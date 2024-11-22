package com.didier.carmanagementsystem.controller;

import com.didier.carmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpolController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/check-email")
    public boolean checkEmail(@RequestParam String email) {
        return employeeService.isEmailExists(email);

    }
}
