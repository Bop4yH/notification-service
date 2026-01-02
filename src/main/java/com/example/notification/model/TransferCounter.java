package com.example.notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "transfer_counter")
@Getter
@Setter
@NoArgsConstructor
public class TransferCounter {

    @Id
    private UUID userId;

    private long transferCount;

    public TransferCounter(UUID userId) {
        this.userId = userId;
        this.transferCount = 0;
    }
}