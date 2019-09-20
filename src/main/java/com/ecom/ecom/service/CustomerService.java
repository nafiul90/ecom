package com.ecom.ecom.service;

import com.ecom.ecom.exception.DoesNotExistsException;
import com.ecom.ecom.model.Customer;
import com.ecom.ecom.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public ResponseEntity<Iterable<Customer>> getAllCustomers() {
        return ResponseEntity.status(200).body(customerRepo.findAll());
    }

    public ResponseEntity<Optional<Customer>> getCustomerById(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(!optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Customer with this id "+id+" does not exists.");
        }
        return ResponseEntity.status(200).body(optionalCustomer);
    }

    public ResponseEntity<Customer> saveCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepo.findByUsername(customer.getUsername());

        //check for the username
        if(optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Username already exists");
        }
        //check for the email
        optionalCustomer = customerRepo.findByEmail(customer.getEmail());
        if(optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Email already exists");
        }

        customerRepo.save(customer);
        return ResponseEntity.status(200).body(customer);

    }

    public ResponseEntity<Customer> updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customer.getId());
        if(!optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Customer with this "+customer.getId()+" does not exists");
        }

        //check for username.
        if(optionalCustomer.get().getUsername() != customer.getUsername()){
            throw new DoesNotExistsException("Username should not be changed.");
        }

        //check for email.
        if(optionalCustomer.get().getEmail() != customer.getEmail()){
            throw new DoesNotExistsException("Email should not be changed.");
        }

        customerRepo.save(customer);

        return ResponseEntity.status(200).body(customer);
    }

    public ResponseEntity<Optional<Customer>> deleteCustomer(long id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(!optionalCustomer.isPresent()){
            throw new DoesNotExistsException("Customer with this id "+id+" does not exists.");
        }

        customerRepo.delete(optionalCustomer.get());
        return ResponseEntity.status(200).body(optionalCustomer);
    }
}
