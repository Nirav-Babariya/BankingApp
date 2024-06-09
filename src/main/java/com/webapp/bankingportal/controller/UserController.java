package com.webapp.bankingportal.controller;

import java.util.HashMap;
import java.util.Map;

import com.webapp.bankingportal.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.bankingportal.dto.LoginRequest;
import com.webapp.bankingportal.dto.UserResponse;
import com.webapp.bankingportal.security.JwtTokenUtil;
import com.webapp.bankingportal.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService) {
        this.userService =  userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody Users users) {
        Users registeredUsers = userService.registerUser(users);
        
        UserResponse userResponse = new UserResponse();
        userResponse.setName(registeredUsers.getName());
        userResponse.setAddress(registeredUsers.getAddress());
        userResponse.setPhone_number(registeredUsers.getPhone_number());
        userResponse.setEmail(registeredUsers.getEmail());
        userResponse.setAccountNumber(registeredUsers.getAccount().getAccountNumber());
        userResponse.setIFSC_code(registeredUsers.getAccount().getIFSC_code());
        userResponse.setBranch(registeredUsers.getAccount().getBranch());
        userResponse.setAccount_type(registeredUsers.getAccount().getAccount_type());
        

        return ResponseEntity.ok(userResponse);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user with the account number and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getAccountNumber(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            // Invalid credentials, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid account number or password");
        }

        // If authentication successful, generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getAccountNumber());
        System.out.println(userDetails);
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> result =  new HashMap<>();
        result.put("token", token);
        // Return the JWT token in the response
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

}
