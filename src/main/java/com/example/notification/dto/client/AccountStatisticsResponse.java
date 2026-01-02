package com.example.notification.dto.client;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class AccountStatisticsResponse {
    long incomingTransfersCount;

    long outgoingTransfersCount;

    @NotNull
    BigDecimal totalReceived;

    @NotNull
    BigDecimal totalSent;
}
