package com.example.online_bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
    
    List<Transactions> findByAccountNo(int accountNo);
}
