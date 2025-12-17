package com.example.online_bank_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.model.Nominee;
import com.example.online_bank_system.service.NomineeService;

@RestController
@RequestMapping("/api/nominee")
public class NomineeController {
    
    @Autowired
    NomineeService nomineeService;


    @PostMapping
    public ResponseEntity<Nominee> add ( @RequestBody Nominee nominee  ) {
        Nominee saved = nomineeService.saveNominee(nominee);
        return ResponseEntity.ok(saved); 
    }

    @DeleteMapping("/delete/{nomineeId}")
    public ResponseEntity<String> delete ( @PathVariable int nomineeId ) {
        boolean b = nomineeService.deleteNominee(nomineeId);
        if ( b == false ) {
            return ResponseEntity.badRequest().body("Delete failed !");
        }

        return ResponseEntity.ok("Nominee deleted !");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Nominee>> list ( @PathVariable int userId ) {
        return ResponseEntity.ok(nomineeService.getNomineeByUser(userId));

    }

}
