package com.didier.carmanagementsystem.service.imp;

import com.didier.carmanagementsystem.model.Employee;
import com.didier.carmanagementsystem.repository.EmployeeRepository;
import com.didier.carmanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByStatus(List<String> statuses) {
        return employeeRepository.findByStatusIn(statuses);
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee.getEmployeeId() != null && employeeRepository.existsById(employee.getEmployeeId())) {
            employeeRepository.save(employee);
        }
    }


    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setStatus("DisActive");
        employeeRepository.save(employee);
    }

    @Override
    public boolean isEmailExists(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public List<Employee> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }


}
