package com.malves.budgeting.infrastructure.persistence.entity;

import java.util.UUID;

import com.malves.budgeting.domain.Category;
import com.malves.budgeting.domain.Transaction;
import com.malves.budgeting.domain.TransactionId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class TransactionEntity {

    @Id 
    private UUID id;
    private String description;
    private long amount;
    private Category category;

    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
            transaction.getId().uuid(),
            transaction.getDescription(),
            transaction.getAmount(),
            transaction.getCategory());
    }

    public Transaction toDomain() {
        return new Transaction(
            new TransactionId(),
            this.description,
            this.amount,
            this.category
        );
    }
}
