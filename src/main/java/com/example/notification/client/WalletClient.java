package com.example.notification.client;

import com.example.notification.dto.client.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "wallet-client", url = "${wallet.service.url}")
public interface WalletClient {

    @GetMapping("/accounts/{id}")
    AccountResponse getAccount(@PathVariable("id") UUID id);
}
