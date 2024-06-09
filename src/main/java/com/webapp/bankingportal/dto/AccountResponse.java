package com.webapp.bankingportal.dto;

import lombok.Data;

@Data
public class AccountResponse {
    private String accountNumber;
    private double balance;
    private String accountType;
    private String branch;
    private String IFSCCode;
}
