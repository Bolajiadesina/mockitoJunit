package com.unittesting.mockitoJunit.service;

import java.util.List;
import java.util.Optional;

import com.unittesting.mockitoJunit.entity.Customer;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(long id);
    Customer updateCustomer(Customer updatedCustomer);
    void deleteCustomer(long id);

    
}
