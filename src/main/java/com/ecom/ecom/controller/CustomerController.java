package com.ecom.ecom.controller;

import com.ecom.ecom.model.Customer;
import com.ecom.ecom.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("customer")
public class CustomerController {

    private final String allowedClientUrl = "https://ns471.csb.app";//"http://localhost:3000";

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Iterable<Customer>> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @CrossOrigin(origins = allowedClientUrl)
    @GetMapping("id/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PostMapping("add/")
    @ResponseBody
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @PutMapping("update/")
    @ResponseBody
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @CrossOrigin(origins = allowedClientUrl)
    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Customer>> deleteCustomerById(@PathVariable long id){
        return customerService.deleteCustomer(id);
    }

}
