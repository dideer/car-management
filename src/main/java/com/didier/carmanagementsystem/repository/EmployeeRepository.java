package com.didier.carmanagementsystem.repository;

import com.didier.carmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByStatusIn(List<String> statuses);
    boolean existsByEmail(String email);

    List<Employee> findByPosition(String position);


}
