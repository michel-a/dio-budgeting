package com.malves.budgeting.application;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import com.malves.budgeting.application.output.TransactionOutput;
import com.malves.budgeting.domain.Category;
import com.malves.budgeting.domain.TransactionRepository;

public class ListTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transaction-by-category", description = "Lista transações financeiras por categoria.")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação.") Category category) {
        return transactionRepository.findAllByCategory(category).stream().map(TransactionOutput::from).toList();
    }
}
