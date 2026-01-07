package com.example.online_bank_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online_bank_system.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNo( int account_no );

    List<Account> findAccountByUserId( int user_id );

    List<Account> findByUserId(int userId);

    List<Account> findByUserIdAndDeletedFalse( int userId );

    List<Account> findByDeletedFalse();

    List<Account> findByDeletedTrue();
}
