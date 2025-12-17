package com.example.online_bank_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Integer>{
    
}
