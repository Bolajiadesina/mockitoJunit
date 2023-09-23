package com.unittesting.mockitoJunit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unittesting.mockitoJunit.entity.Customer;
import com.unittesting.mockitoJunit.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> savedCustomer = customerRepository.findByEmail(customer.getEmail());
        if (savedCustomer.isPresent()) {
            throw new ResourceNotFoundException(null, "Customer already exist with given email:" + customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

}
