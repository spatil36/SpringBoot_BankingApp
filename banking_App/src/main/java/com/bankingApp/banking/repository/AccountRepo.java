package com.bankingApp.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.banking.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
    
}
