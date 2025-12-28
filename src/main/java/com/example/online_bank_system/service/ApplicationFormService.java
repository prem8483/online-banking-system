package com.example.online_bank_system.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.model.ApplicationForm;
import com.example.online_bank_system.model.Nominee;
import com.example.online_bank_system.model.User;
import com.example.online_bank_system.repository.AccountRepository;
import com.example.online_bank_system.repository.ApplicationFormRepository;
import com.example.online_bank_system.repository.NomineeRepository;
import com.example.online_bank_system.repository.UserRepository;

@Service
public class ApplicationFormService {
    
    @Autowired
    private ApplicationFormRepository applicationFormRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ApplicationForm submitForm ( ApplicationForm form ) {
        form.setStatus("PENDING");
        form.setCreatedAt(LocalDateTime.now());
        return applicationFormRepository.save( form );
    }

    public List<ApplicationForm> getPendingForms ( ) {
        return applicationFormRepository.findByStatus("PENDING");
    }

    public ApplicationForm getFormById ( int id ) {
        return applicationFormRepository.findById( id ).orElse(null);
    }

    public Map<String ,String> approveForm( int id ) {
        ApplicationForm form = applicationFormRepository.findById(id).orElse(null);
        if ( form == null ) {
            return null;
        }

        form.setStatus("APPROVED");
        applicationFormRepository.save(form);
        
        String password = adminService.generateRandomPassword();

        User user = new User();

        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setPhone(form.getPhone());
        user.setAddress(form.getAddress());
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        user.setAadharNumber(form.getAadhaarNumber());
        user.setDob(form.getDob());
        user.setGender(form.getGender());
        user.setNationality(form.getNationality());
        user.setOccupation(form.getOccupation());
        user.setAnnualIncome(form.getAnnualIncome());
        user.setMaritalStatus(form.getMaritalStatus());
        user.setPanNumber(form.getPanNumber());
        user.setAccountType(form.getAccountType());
        user.setState(form.getState());
        user.setCity(form.getCity());
        user.setPincode(form.getPincode());

        User savedUser = userRepository.save(user);

        Account account = new Account();

        account.setUserId(savedUser.getUserId());
        account.setStatus(true);
        account.setBalance(0);
        account.setCreatedAt(LocalDateTime.now());
        account.setAccountType(savedUser.getAccountType());

        Account savedAccount = accountRepository.save(account);

        Nominee nominee = new Nominee();

        nominee.setNomineeName(form.getNomineeName());
        nominee.setNomineeAccountNo(form.getNomineeAccountNo());
        nominee.setRelation(form.getRelation());
        nominee.setBankName(form.getBankName());
        nominee.setIfscCode(form.getIfscCode());
        nominee.setUserId(savedUser.getUserId());

        nomineeRepository.save(nominee);

        Map<String, String> result = new HashMap<>();
        result.put("Email", savedUser.getEmail());
        result.put("Password", savedUser.getPassword());
        result.put("account_no", String.valueOf(savedAccount.getAccountNo()));

        return result;
        
    }
    
    public boolean rejectForm ( int id ) {
        ApplicationForm applicationForm = applicationFormRepository.findById(id).orElse(null);
        if ( applicationForm ==  null ) {
            return false;
        }

        applicationForm.setStatus("REJECTED");
        applicationFormRepository.save(applicationForm);

        return true;
    }
    
    public List<ApplicationForm> getApprovedForms (  ) {
        return applicationFormRepository.findByStatus("APPROVED");
    }

    public List<ApplicationForm> getRejectedForms( ) {
        return applicationFormRepository.findByStatus("REJECTED");
    }
}
