package com.example.notification.service;

import com.example.notification.dto.event.TransferCompletedEvent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptService {

    private final Configuration freemarkerConfig;

    public void generateAndSave(TransferCompletedEvent event, String senderName, String currency) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("transferId", event.getTransferId());
            data.put("date", LocalDateTime.now().toString());
            data.put("senderName", senderName);
            data.put("receiverId", event.getToAccountId());
            data.put("amount", event.getAmount());
            data.put("currency", currency);

            Template template = freemarkerConfig.getTemplate("receipt.ftl");

            StringWriter writer = new StringWriter();
            template.process(data, writer);
            String htmlContent = writer.toString();

            String fileName = "receipt_" + event.getTransferId() + ".html";
            Path path = Paths.get("receipts", fileName);

            Files.createDirectories(path.getParent());
            Files.writeString(path, htmlContent);

            log.info("Receipt generated: {}", path.toAbsolutePath());

        } catch (Exception e) {
            log.error("Failed to generate receipt", e);
        }
    }
}
