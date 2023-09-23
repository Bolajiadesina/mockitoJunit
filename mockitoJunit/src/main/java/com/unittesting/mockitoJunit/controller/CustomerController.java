package com.unittesting.mockitoJunit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unittesting.mockitoJunit.entity.Customer;
import com.unittesting.mockitoJunit.service.CustomerService;

@RestController
@RequestMapping("/api/customer")

public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") long customerId) {
        return customerService.getCustomerById(customerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long customerId,
            @RequestBody Customer customer) {
        return customerService.getCustomerById(customerId)
                .map(savedCustomer -> {

                    savedCustomer.setFirstName(customer.getFirstName());
                    savedCustomer.setLastName(customer.getLastName());
                    savedCustomer.setEmail(customer.getEmail());

                    Customer updatedCustomer = customerService.updateCustomer(savedCustomer);
                    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long customerId) {

        customerService.deleteCustomer(customerId);

        return new ResponseEntity<String>("Customer deleted successfully!.", HttpStatus.OK);

    }

}
