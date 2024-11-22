package com.didier.carmanagementsystem.controller;

import com.didier.carmanagementsystem.model.Employee;
import com.didier.carmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ceo/employees")
public class EmployeeController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private EmployeeService employeeService;

//    @GetMapping("/")
//    public String listEmployees(@ModelAttribute("employee") Employee employee, Model model) {
//
//
//
//
//        model.addAttribute("employees", employeeService.getActiveEmployees());
//        model.addAttribute("employee", new Employee()); // Ensure this is added
//        return "employee";
//    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute Employee employee) {

        if (employee.getStatus() == null || employee.getStatus().isEmpty()) {
            employee.setStatus("Active");
        }
        employeeService.saveEmployee(employee);

        return "redirect:/ceo/employees/";
    }


    @GetMapping("/")
    public String getEmployeesByStatus(@ModelAttribute("employee") Employee employee, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);


        // Define statuses you want to filter by
        List<String> statuses = Arrays.asList("Active", "Blocked");

        List<Employee> employees = employeeService.getEmployeesByStatus(statuses);
        model.addAttribute("employees", employees);
        return "ceo/employee"; // This is your Thymeleaf template name
    }

    @GetMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(employeeId);
            redirectAttributes.addFlashAttribute("message", "Employee status updated to Disactive.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating employee status.");
        }
        return "redirect:/ceo/employees/"; // Redirect back to the employee list
    }

    @PostMapping("/update-employee")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/ceo/employees/"; // Redirect to the list of employees after updating
    }
}
