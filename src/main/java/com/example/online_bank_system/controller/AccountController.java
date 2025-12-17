package com.example.online_bank_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount( @RequestBody Account account ) {
        Account a = accountService.createAccount(account);
        return ResponseEntity.ok(a);
    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity<Account> getAccountByAccNo ( @PathVariable int accountNo) {
        Account a = accountService.findByAccountNo( accountNo );
        if ( a == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(a);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getAccountsByUser ( @PathVariable int userId ) {
        List<Account> list = accountService.findByUserId( userId );
        return ResponseEntity.ok(list);
    }

    @GetMapping("/allAccounts")
    public ResponseEntity<List<Account>> showAllAccounts ( ) {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}
