package com.example.online_bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.model.User;
import com.example.online_bank_system.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService usersService;

    //Register new user
    @PostMapping("/register")
    public ResponseEntity<User> register( @RequestBody User user) {
        User saved = usersService.createUser(user);
        return ResponseEntity.ok(saved);
    }

    //Login
    @PostMapping ("/login")
    public ResponseEntity<User> login( @RequestBody LoginRequest req ) {
        User u = usersService.loginUser(req.getEmail(), req.getPassword());
        if (u == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(u);
    }

    //Get User by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getById( @PathVariable int id ) {
       User u =  usersService.findUserById( id );
        if ( u == null ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }

    //Update Profile
    @PutMapping("/{id}")
    public ResponseEntity<User> update( @PathVariable int id, @RequestBody User user ) {
        user.setUserId(id);
        User updated = usersService.updateUser(user);
        return ResponseEntity.ok(updated);
    }

    // DTO for login request (inner static class for simplicity)
    public static class LoginRequest {

        private String email;
        private String password;

        public String getEmail () {
            return email;
        }        

        public void setEmail ( String email ) {
            this.email = email;
        }

        public String getPassword () {
            return password;
        }

        public void setPassword ( String password ) {
            this.password = password;
        }

    }
}
