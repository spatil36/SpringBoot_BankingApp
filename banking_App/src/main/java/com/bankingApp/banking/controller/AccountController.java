package com.bankingApp.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingApp.banking.dto.AccountDto;
import com.bankingApp.banking.entity.Account;
import com.bankingApp.banking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accService;

    //ADD Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAcc(@RequestBody AccountDto acc){
        return new ResponseEntity<>(accService.createAccount(acc), HttpStatus.CREATED);
    }

    //GET Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accnt = accService.getAccountById(id);
        return new ResponseEntity<>(accnt, HttpStatus.OK);
    }

    //DEPOSIT REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> req){
        AccountDto accDep = accService.deposit(id, req.get("amount"));
        return new ResponseEntity<AccountDto>(accDep, HttpStatus.OK);
    }

    //Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> req){

        AccountDto acc = accService.withdraw(id, req.get("amount"));
        return new ResponseEntity<AccountDto>(acc, HttpStatus.OK);
    }

    //GETALL Accounts REST API
    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAllAccnts(){
        List<AccountDto> lst = accService.getAllAccounts();
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }

    //DELETE Account REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAcc(@PathVariable Long id){

        accService.deleteAcc(id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);

    }
}
