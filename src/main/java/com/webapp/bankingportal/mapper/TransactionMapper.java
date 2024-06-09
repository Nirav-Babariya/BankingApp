package com.webapp.bankingportal.mapper;

import org.springframework.stereotype.Component;

import com.webapp.bankingportal.dto.TransactionDTO;
import com.webapp.bankingportal.entity.Transaction;

@Component
public class TransactionMapper {

    public TransactionDTO toDto(Transaction transaction) {
        TransactionDTO dto = TransactionDTO.builder()
                .id(transaction.getId())
                .accountNumber(transaction.getSourceAccount().getAccountNumber())
                .amount(transaction.getAmount())
                .transaction_date(transaction.getTransaction_date())
                .transaction_type(transaction.getTransactionType())
                .build();

        return dto;
    }
}
