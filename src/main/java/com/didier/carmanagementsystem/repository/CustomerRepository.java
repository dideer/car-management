package com.didier.carmanagementsystem.repository;

import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByStatus(String status);

}
