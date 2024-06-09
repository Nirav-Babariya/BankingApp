package com.webapp.bankingportal.dto;

import lombok.Data;

@Data
public class UserResponse  {
    
    private String name;
    private String email;
    private String address;
    private String phone_number;
    private String accountNumber;
    private String IFSC_code;
    private String branch;
    private String account_type;
    
}
