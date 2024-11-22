package com.didier.carmanagementsystem.controller;

import com.didier.carmanagementsystem.model.Employee;
import com.didier.carmanagementsystem.model.User;
import com.didier.carmanagementsystem.service.EmployeeService;
import com.didier.carmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/ceo/users/")
    public String listUser(@ModelAttribute("users") User user, Model model, Principal principal) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);


        model.addAttribute("userss", userService.getAllUsers());
        model.addAttribute("users", new User()); // Ensure this is added

        List<String> statuses = Arrays.asList("Active", "Blocked");

        List<Employee> employees = employeeService.getEmployeesByStatus(statuses);
        model.addAttribute("employees", employees);
        return "ceo/user";
    }

    @PostMapping("ceo/users/save-user")
    public String saveUser(@ModelAttribute  User user, Model model) {
        userService.saveUser(user);
        return "redirect:/ceo/users/";
    }

    @GetMapping("/users/login")
    public String login(){
        return "login";
    }











}
