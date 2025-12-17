package com.example.online_bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.Nominee;

public interface NomineeRepository extends JpaRepository<Nominee, Integer> {

    List<Nominee> findByUserId( int user_id );
}
