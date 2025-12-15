package com.example.notification;

import com.example.notification.event.TransferCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "transfer-notifications")
    public void handleTransfer(TransferCompletedEvent event) {
        log.info("NOTIFICATION SERVICE: Получено событие о переводе!");
        log.info("Transfer ID: {}", event.getTransferId());
        log.info("Amount: {}", event.getAmount());
    }
}