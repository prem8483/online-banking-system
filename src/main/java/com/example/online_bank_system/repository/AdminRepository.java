package com.example.online_bank_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    
    Admin findByUsernameAndPassword(String username, String password);

}
