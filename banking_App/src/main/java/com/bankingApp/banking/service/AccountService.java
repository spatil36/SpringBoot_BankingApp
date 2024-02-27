package com.bankingApp.banking.service;

import java.util.List;

import com.bankingApp.banking.dto.AccountDto;
import com.bankingApp.banking.entity.Account;

public interface AccountService {
    
    AccountDto createAccount(AccountDto acc);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAcc(Long id);
}
