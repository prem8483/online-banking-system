package com.example.online_bank_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_bank_system.model.ApplicationForm;
import com.example.online_bank_system.service.ApplicationFormService;

@RestController
@RequestMapping("/api/forms")
public class ApplicationFormController {
    
    @Autowired
    ApplicationFormService applicationFormService;

    @PostMapping("/apply")
    public ResponseEntity<ApplicationForm> save ( @RequestBody ApplicationForm form  ) {
        ApplicationForm applicationForm = applicationFormService.submitForm(form);
        return ResponseEntity.ok(applicationForm);
    }

    @GetMapping("/admin/application/pending")
    public ResponseEntity<List<ApplicationForm>> pendingForms ( ) {
        List<ApplicationForm> applicationForm = applicationFormService.getPendingForms();
        return ResponseEntity.ok(applicationForm);
    }

    @GetMapping("/admin/application/{id}")
    public ResponseEntity<ApplicationForm> getFormById ( @PathVariable int id ) {
        ApplicationForm applicationForm = applicationFormService.getFormById(id);
        if (applicationForm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(applicationForm);
    }

    @GetMapping("/admin/application/approve/{id}")
    public ResponseEntity<?> approveById(@PathVariable int id) {
        Map<String, String> result = applicationFormService.approveForm(id);
        if (result == null) {
            return ResponseEntity.badRequest().body("Application Not Found");
        }
        return ResponseEntity.ok(result);
    }

    // @GetMapping("/admin/application/reject/{id}")
    // public ResponseEntity<String> rejectFormsById ( @PathVariable int id ) {
    //     boolean check = applicationFormService.rejectForm(id);
    //     if ( check == false ) {
    //         return ResponseEntity.badRequest().body("Application Not Found");
    //     }

    //     return ResponseEntity.ok("REJECTED");
    // }

    @GetMapping("/admin/application/approvedShow") 
    public ResponseEntity<List<ApplicationForm>> showApprovedForms (  ) {
        List<ApplicationForm> approved = applicationFormService.getApprovedForms();
        return ResponseEntity.ok(approved);
    }

    @GetMapping("/admin/application/rejectedForms")
    public ResponseEntity<List<ApplicationForm>> showRejectedForms( ) {
        List<ApplicationForm> rejected = applicationFormService.getRejectedForms();
        return ResponseEntity.ok(rejected);
    }
}
