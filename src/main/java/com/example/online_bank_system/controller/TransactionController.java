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

import com.example.online_bank_system.model.Transactions;
import com.example.online_bank_system.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transactions> create( @RequestBody Transactions transactions ) {
        Transactions saved = transactionService.saveTransactions(transactions);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity<List<Transactions>> byAccount ( @PathVariable int accountNo ) {
        List<Transactions> list = transactionService.getTransactionsByAccount(accountNo);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/txn/{id}")
    public ResponseEntity<Transactions> getOne( @PathVariable int id ) {
        Transactions t = transactionService.getTransactionsById(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> all () {
        List<Transactions> t = transactionService.getAllTransactions();
        return ResponseEntity.ok(t);
    }
}
