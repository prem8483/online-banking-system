package com.example.online_bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailAndPassword( String email, String password );

    List<Account> findByUserId( int userId );

    List<User> findByDeletedFalse( );

    List<User> findByDeletedTrue( );
}
