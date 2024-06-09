package com.webapp.bankingportal.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webapp.bankingportal.entity.Users;
import com.webapp.bankingportal.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        Users users = userRepository.findByAccountAccountNumber(accountNumber);
        if (users == null) {
            throw new UsernameNotFoundException("Invalid account number");
        }

        // Return a UserDetails object that wraps the User entity
        return new org.springframework.security.core.userdetails.User(
                users.getAccount().getAccountNumber(),  // Use account number as the username
                users.getPassword(),
                Collections.emptyList()
        );
    }	
}
