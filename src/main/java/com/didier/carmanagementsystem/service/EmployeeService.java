package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee); // Create or Update

    List<Employee> getAllEmployees(); // Read all

    List<Employee> getEmployeesByStatus(List<String> statuses);

//    Optional<Employee> getEmployeeById(Long id); // Read by ID
//
    void updateEmployee(Employee employee); // Update
//
    void deleteEmployee(Long id); // Delete
    boolean isEmailExists(String email);

    List<Employee> getEmployeesByPosition(String position);

}
