package com.malves.budgeting.infrastructure.http.request;

import com.malves.budgeting.application.input.PersistTransactionInput;
import com.malves.budgeting.domain.Category;

public record TransactionRequest(String description, Category category, long amount) {

    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }

}