package com.malves.budgeting.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.malves.budgeting.domain.Category;
import com.malves.budgeting.infrastructure.persistence.entity.TransactionEntity;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}
