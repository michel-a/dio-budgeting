package com.malves.budgeting.infrastructure.http.response;

import com.malves.budgeting.application.output.TransactionOutput;

public record TransactionResponse(String id, String category, String description, double amount) {
    public static TransactionResponse from(TransactionOutput transactionOutput) {
        return new TransactionResponse(
            transactionOutput.id(),
            transactionOutput.category(),
            transactionOutput.description(),
            transactionOutput.value()
        );
    }
}
