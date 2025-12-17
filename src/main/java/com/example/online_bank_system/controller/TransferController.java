package com.example.online_bank_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.dto.TransferRequest;
import com.example.online_bank_system.service.TransferService;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer ( @RequestBody TransferRequest data ) {
        transferService.transfer(data);
        return ResponseEntity.ok("Transfer Successfull");
    }
}
