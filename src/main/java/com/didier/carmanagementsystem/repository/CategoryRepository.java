package com.didier.carmanagementsystem.repository;

import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findEmployeeByStatus(String status);
}
