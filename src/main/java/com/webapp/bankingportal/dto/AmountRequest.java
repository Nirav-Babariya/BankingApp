package com.webapp.bankingportal.dto;

import lombok.Data;

@Data
public class AmountRequest {
    private String accountNumber;
    private double amount;
    
}
