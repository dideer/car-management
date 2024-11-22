package com.didier.carmanagementsystem.service;

import com.didier.carmanagementsystem.model.Category;
import com.didier.carmanagementsystem.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getALlCustomers();
    List<Customer> getCustomerByStatus(String status);
}
