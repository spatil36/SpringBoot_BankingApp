package com.bankingApp.banking.mapper;

import com.bankingApp.banking.dto.AccountDto;
import com.bankingApp.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accDto = new AccountDto(
            account.getId(),
            account.getAccHolderName(),
            account.getBalance()
        );

        return accDto;

    }
    
}
