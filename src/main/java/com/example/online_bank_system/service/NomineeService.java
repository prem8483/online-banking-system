package com.example.online_bank_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.Nominee;
import com.example.online_bank_system.repository.NomineeRepository;

@Service
public class NomineeService {
    
    @Autowired
    NomineeRepository nomineeRepository;

    public Nominee saveNominee( Nominee b ) {
        return nomineeRepository.save(b);
    }

    public boolean deleteNominee ( int benId ) {
        if (!nomineeRepository.existsById(benId)) {
            return false;
        }

        nomineeRepository.deleteById(benId);
        return true;
    }

    public List<Nominee> getNomineeByUser( int userId ) {
        return nomineeRepository.findByUserId(userId);
    }
}
