package com.webapp.bankingportal.service;

import com.webapp.bankingportal.entity.Users;
import com.webapp.bankingportal.exception.UserValidation;
import com.webapp.bankingportal.util.LoggedinUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.webapp.bankingportal.entity.Account;
import com.webapp.bankingportal.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, AccountService accountService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountService = accountService;
        this.passwordEncoder =  passwordEncoder;
    }
    
    @Override
    public Users registerUser(Users users) {
        
    	 String encodedPassword = passwordEncoder.encode(users.getPassword());
         users.setPassword(encodedPassword);

        // Save the user details
        Users savedUsers = userRepository.save(users);

        // Create an account for the user
        Account account = accountService.createAccount(savedUsers);

        savedUsers.setAccount(account);
        userRepository.save(savedUsers);
        
        System.out.println(savedUsers.getAccount().getAccountNumber());
        System.out.println(account.getUser().getName());

        
        return savedUsers;
    }


}
