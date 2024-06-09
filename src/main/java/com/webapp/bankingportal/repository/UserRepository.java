package com.webapp.bankingportal.repository;

import com.webapp.bankingportal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByAccountAccountNumber(String accountNumber);
}
