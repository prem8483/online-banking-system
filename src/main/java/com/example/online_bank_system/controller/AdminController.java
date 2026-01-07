package com.example.online_bank_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.model.Admin;
import com.example.online_bank_system.model.Transactions;
import com.example.online_bank_system.model.User;
import com.example.online_bank_system.service.AdminService;
import com.example.online_bank_system.service.ApplicationFormService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ApplicationFormService applicationFormService;

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody AdminLogin req) {
        Admin a = adminService.login(req.getUsername(), req.getPassword());
        if (a == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(a);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        List<User> a = adminService.getAllUsers();
        if (a == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(a);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> allAccounts() {
        List<Account> a = adminService.getAllAccountsDeletedFalse();
        if (a == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(a);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transactions>> allTransactions() {
        List<Transactions> transactions = adminService.getAllTransactions();
        if (transactions == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/block/{id}")
    public ResponseEntity<String> blockUser(@PathVariable int id) {
        boolean u = adminService.blockUser(id);
        if (u == false) {
            return ResponseEntity.badRequest().body("User is not blocked !");
        }

        return ResponseEntity.ok("User is Blocked");
    }

    @PostMapping("/unblock/{id}")
    public ResponseEntity<String> unblockUser(@PathVariable int id) {
        boolean u = adminService.unblockUser(id);
        if (u == false) {
            return ResponseEntity.badRequest().body("User is not unblocked");
        }

        return ResponseEntity.ok("User is unblocked !!");
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        boolean u = adminService.deleteUser(id);
        if (u == false) {
            return ResponseEntity.badRequest().body("Unable to Delete User !!");
        }

        return ResponseEntity.ok("User is deleted !");
    }

    public static class AdminLogin {

        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @GetMapping("/application/approve/{id}")
    public ResponseEntity<String> approveId(@PathVariable int id) {
        Map<String, String> result = applicationFormService.approveForm(id);

        if (result == null) {
            return ResponseEntity.badRequest().body("Application not found!!");
        }

        return ResponseEntity.ok("APPROVED");
    }

    @GetMapping("/application/reject/{id}")
    public ResponseEntity<String> rejectById(@PathVariable int id) {
        boolean ok = applicationFormService.rejectForm(id);
        if (!ok) {
            return ResponseEntity.badRequest().body("Application Not Found");
        }
        return ResponseEntity.ok("REJECTED");
    }

    @GetMapping("/user/showUsers")
    public ResponseEntity<List<User>> showAllUsers ( ) {
        List<User> admin = adminService.getUserDetails();
        if ( admin == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(admin);
    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<User> saveEditUserDetails ( @PathVariable int userId, @RequestBody User data ) {
        User updated = adminService.editUserProfile(userId, data);
        if ( updated == null ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> showUser ( @PathVariable int userId ) {
        User u = adminService.getUserById(userId);
        if( u == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(u);
    }

    @GetMapping("/deletedRecords")
    public ResponseEntity<List<Account>> showDeletedRecords ( ) {
        List<Account> data = adminService.getAllDeletedAccountsAndUser();
        if (data == null ) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }
}
