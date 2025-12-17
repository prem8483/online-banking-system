package com.example.online_bank_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;

    public Account createAccount ( Account account ) {
        account.setCreatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public Account findByAccountNo ( int account_no ) {
        return accountRepository.findByAccountNo(account_no);
    }

    public List<Account> findByUserId ( int user_id ) {
        return accountRepository.findAccountByUserId( user_id );
    }

    public List<Account> getAllAccounts ( ) {
        return accountRepository.findAll();
    }

    public Account save ( Account account ) {
        return accountRepository.save(account);
    }
}
