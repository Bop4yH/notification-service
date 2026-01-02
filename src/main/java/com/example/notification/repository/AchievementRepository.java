package com.example.notification.repository;

import com.example.notification.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {

    boolean existsByUserIdAndName(UUID userId, String name);
}