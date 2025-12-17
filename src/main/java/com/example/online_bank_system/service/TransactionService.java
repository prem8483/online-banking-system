package com.example.online_bank_system.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.Transactions;
import com.example.online_bank_system.repository.TransactionsRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepository transactionsRepository;
    
    public Transactions saveTransactions ( Transactions txn ) {
        txn.setCreatedAt(LocalDateTime.now());
        return transactionsRepository.save(txn);
    }

    public List<Transactions> getTransactionsByAccount ( int accountNo ) {
        return transactionsRepository.findByAccountNo(accountNo);
    }

    public Transactions getTransactionsById ( int txnId ) {
        return transactionsRepository.findById(txnId).orElse(null);
    }

    public List<Transactions> getAllTransactions ( ) {
        return transactionsRepository.findAll();
    }
}
