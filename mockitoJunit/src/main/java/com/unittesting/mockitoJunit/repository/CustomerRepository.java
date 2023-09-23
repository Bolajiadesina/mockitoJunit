package com.unittesting.mockitoJunit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.unittesting.mockitoJunit.entity.Customer;


@Service
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    @Query(value="SELECT t FROM CUSTOMER t WHERE t.EMAIL='email'",  nativeQuery=true )
    Optional<Customer> findByEmail(String email);
    
}
