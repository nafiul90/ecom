package com.ecom.ecom.repository;

import com.ecom.ecom.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer,Long> {
    public Optional<Customer> findByUsername(String username);
    public Optional<Customer> findByEmail(String email);
}
