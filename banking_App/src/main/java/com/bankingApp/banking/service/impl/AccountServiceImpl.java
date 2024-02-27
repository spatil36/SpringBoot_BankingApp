package com.bankingApp.banking.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingApp.banking.dto.AccountDto;
import com.bankingApp.banking.entity.Account;
import com.bankingApp.banking.mapper.AccountMapper;
import com.bankingApp.banking.repository.AccountRepo;
import com.bankingApp.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepo accRepo;

    @Override
    public AccountDto createAccount(AccountDto acc) {
        
        Account account = AccountMapper.mapToAccount(acc);
        Account savedAcc = accRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAcc);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account accnt = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

        return AccountMapper.mapToAccountDto(accnt);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        
        Account accnt = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

        double total = accnt.getBalance() + amount;
        accnt.setBalance(total);
        Account savedAccnt = accRepo.save(accnt);
        return AccountMapper.mapToAccountDto(savedAccnt);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        
        Account accnt = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

        if(accnt.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }

        double total = accnt.getBalance() - amount;
        accnt.setBalance(total);
        Account savedAccnt = accRepo.save(accnt);

        return AccountMapper.mapToAccountDto(savedAccnt);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        
        List<Account> accnts = accRepo.findAll();
        return accnts.stream().map((accnt) -> AccountMapper.mapToAccountDto(accnt)).collect(Collectors.toList());
        
    }

    @Override
    public void deleteAcc(Long id) {
        
        Account accnt = accRepo.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));

        accRepo.deleteById(id);
    }


    
}
