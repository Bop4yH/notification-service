package com.example.notification.service;

import com.example.notification.client.WalletClient;
import com.example.notification.dto.client.AccountResponse;
import com.example.notification.dto.event.TransferCompletedEvent;
import com.example.notification.model.ProcessedEvent;
import com.example.notification.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final WalletClient walletClient;

    private final ReceiptService receiptService;

    private final ProcessedEventRepository processedEventRepository;

    @KafkaListener(topics = "transfer-notifications")
    @Transactional
    public void handleTransfer(TransferCompletedEvent event) {
        UUID eventId = event.getTransferId();

        if (processedEventRepository.existsById(eventId)) {
            log.warn("Event {} already processed. Skipping.", eventId);
            return;
        }
        log.info("Processing event: {}", eventId);

        try {
            AccountResponse sender = walletClient.getAccount(event.getFromAccountId());
            receiptService.generateAndSave(event, sender.getOwnerName(), sender.getCurrency());

            processedEventRepository.save(new ProcessedEvent(eventId));
        } catch (Exception e) {
            log.error("Error processing event", e);
            throw e;
        }
    }
}