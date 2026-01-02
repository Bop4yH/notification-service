package com.example.notification.service;

import com.example.notification.client.WalletClient;
import com.example.notification.model.Achievement;
import com.example.notification.model.TransferCounter;
import com.example.notification.repository.AchievementRepository;
import com.example.notification.repository.TransferCounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementService {

    private final TransferCounterRepository stateRepo;

    private final AchievementRepository achievementRepo;

    private final WalletClient walletClient;

    @Transactional
    public void processTransfer(UUID userId) {
        TransferCounter transferCounter = stateRepo.findById(userId)
                .orElse(new TransferCounter(userId));

        transferCounter.setTransferCount(transferCounter.getTransferCount() + 1);
        stateRepo.save(transferCounter);

        if (transferCounter.getTransferCount() % 6 == 0) {
            checkAndAward(userId);
        }
    }

    private void checkAndAward(UUID userId) {
        var stats = walletClient.getAccountStats(userId);

        for (AchievementType type : AchievementType.values()) {
            checkRule(userId, type.name(), type.check(stats));
        }
    }

    private void checkRule(UUID userId, String achievementName, boolean conditionMet) {
        if (conditionMet && !achievementRepo.existsByUserIdAndName(userId, achievementName)) {
            Achievement achievement = Achievement.builder()
                    .userId(userId)
                    .name(achievementName)
                    .awardedAt(LocalDateTime.now())
                    .build();
            achievementRepo.save(achievement);
            log.info("🏆🏆🏆 ACHIEVEMENT UNLOCKED: User {} got '{}'", userId, achievementName);
        }

    }
}