package com.webapp.bankingportal.dto;

import java.util.Date;

import com.webapp.bankingportal.entity.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    private Long id; 
    private double amount;
    private TransactionType transaction_type;
    private Date transaction_date;
    private String accountNumber;
}