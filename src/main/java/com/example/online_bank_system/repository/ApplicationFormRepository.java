package com.example.online_bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.ApplicationForm;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Integer > {
    
    List<ApplicationForm> findByStatus( String status );
}
