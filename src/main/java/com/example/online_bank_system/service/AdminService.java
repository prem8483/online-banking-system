package com.example.online_bank_system.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.model.Admin;
import com.example.online_bank_system.model.Transactions;
import com.example.online_bank_system.model.User;
import com.example.online_bank_system.repository.AccountRepository;
import com.example.online_bank_system.repository.AdminRepository;
import com.example.online_bank_system.repository.TransactionsRepository;
import com.example.online_bank_system.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    // LOGIN
    public Admin login(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password);
    }

    // ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ALL ACCOUNTS
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ALL TRANSACTIONS
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    // BLOCK USER
    public boolean blockUser(int userId) {
        User u = userRepository.findById(userId).orElse(null);
        if (u == null) return false;
        u.setStatus(true);
        userRepository.save(u);
        return true;
    }

    // UNBLOCK USER
    public boolean unblockUser(int userId) {
        User u = userRepository.findById(userId).orElse(null);
        if (u == null) return false;
        u.setStatus(false);
        userRepository.save(u);
        return true;
    }

    // DELETE USER
    public boolean deleteUser(int userId) {
        User u = userRepository.findById(userId).orElse(null);
        if (u == null) return false;
        userRepository.delete(u);
        return true;
    }

    // RANDOM PASSWORD GENERATOR
    public String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }


    //Show User Profile
    public List<User> getUserDetails () {
        return userRepository.findAll();
    }

    //Edit User Profile
    public User editUserProfile ( int userId, User newData ) {
        User existing = userRepository.findById(userId).orElse(null);
        if ( existing == null ) {
            return null;
        }

        existing.setName(newData.getName());
        existing.setPhone(newData.getPhone());
        existing.setAddress(newData.getAddress());
        existing.setEmail(newData.getEmail());

        return userRepository.save(existing);
    }

    //Get User
    public User getUserById ( int userId ) {
        return userRepository.findById(userId).orElse(null);
    }
}
