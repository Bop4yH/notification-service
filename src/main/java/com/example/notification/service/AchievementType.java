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
    GENEROUS(s -> s.getTotalSent().compareTo(s.getTotalReceived()) > 0),
    SPAMMER(s -> s.getOutgoingTransfersCount() > 100 && s.getTotalSent().compareTo(BigDecimal.valueOf(10)) < 0),
    ZERO_BALANCE(s -> s.getTotalSent().compareTo(s.getTotalReceived()) == 0 && s.getTotalReceived()
            .compareTo(BigDecimal.ZERO) > 0),
    PALINDROME(s -> s.getTotalSent().toBigInteger().toString()
            .contentEquals(new StringBuilder(s.getTotalSent().toBigInteger().toString()).reverse())
            && s.getTotalSent().compareTo(BigDecimal.TEN) > 0);

    private final Predicate<AccountStatisticsResponse> condition;

    public boolean check(AccountStatisticsResponse stats) {
        return condition.test(stats);
    }
}
