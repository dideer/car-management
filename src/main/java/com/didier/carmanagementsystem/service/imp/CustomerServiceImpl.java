package com.didier.carmanagementsystem.service.imp;

import com.didier.carmanagementsystem.model.Customer;
import com.didier.carmanagementsystem.repository.CustomerRepository;
import com.didier.carmanagementsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getALlCustomers() {
        return customerRepository.findAll();

    }

    @Override
    public List<Customer> getCustomerByStatus(String status) {
        return customerRepository.findCustomerByStatus(status);
    }


}
