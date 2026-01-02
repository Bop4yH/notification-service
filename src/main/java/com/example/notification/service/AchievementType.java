package com.example.notification.service;

import com.example.notification.dto.client.AccountStatisticsResponse;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Predicate;

@RequiredArgsConstructor
public enum AchievementType {
    MAGNET(s -> s.getIncomingTransfersCount() > 50),
    MILLIONAIRE(s -> s.getTotalReceived().compareTo(BigDecimal.valueOf(1_000_000)) > 0),
    STARTER(s -> s.getTotalReceived().compareTo(BigDecimal.valueOf(100)) > 0),
    GENEROUS(s -> s.getTotalSent().compareTo(s.getTotalReceived()) > 0);

    private final Predicate<AccountStatisticsResponse> condition;

    public boolean check(AccountStatisticsResponse stats) {
        return condition.test(stats);
    }
}
