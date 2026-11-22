package com.malves.budgeting.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.malves.budgeting.domain.Category;
import com.malves.budgeting.domain.Transaction;
import com.malves.budgeting.domain.TransactionRepository;
import com.malves.budgeting.infrastructure.persistence.entity.TransactionEntity;

@Repository 
public class JpaTransactionRepoitory implements TransactionRepository {

    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepoitory(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        var entity = TransactionEntity.from(transaction);
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {
        return transactionEntityRepository.findAllByCategory(category)
            .stream()
            .map(TransactionEntity::toDomain)
            .toList();
    }
    
}
