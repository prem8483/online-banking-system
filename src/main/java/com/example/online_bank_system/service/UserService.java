package com.example.online_bank_system.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.User;
import com.example.online_bank_system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminService adminService;

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        String password = adminService.generateRandomPassword();
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User loginUser(String email, String password) {
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return null;
        }

        if (!u.getPassword().equals(password)) {
            return null;
        }

        return u;
    }

    public User updateUser(User reqUser) {
        User dbUser = userRepository.findById(reqUser.getUserId()).orElse(null);
        if (dbUser == null)
            return null;

        if (reqUser.getName() != null) {
            dbUser.setName(reqUser.getName());
        }

        if (reqUser.getPhone() != null) {
            dbUser.setPhone(reqUser.getPhone());
        }

        if (reqUser.getAddress() != null) {
            dbUser.setAddress(reqUser.getAddress());
        }
        if (reqUser.getPassword() != null) {
            dbUser.setPassword(reqUser.getPassword());
        }

        return userRepository.save(dbUser);
    }

}
