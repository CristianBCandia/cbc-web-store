package com.cbc.webstore.controller;

import com.cbc.webstore.dto.CustomerDTO;
import com.cbc.webstore.mapper.CustomerMapper;
import com.cbc.webstore.model.Customer;
import com.cbc.webstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GET all customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return null; //CustomerMapper.toCustomerDTO(customers);
    }

    // GET customer by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(CustomerMapper.toCustomerDTO(customer));
    }

    // CREATE new customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(CustomerMapper.toCustomerDTO(createdCustomer), HttpStatus.CREATED);
    }

    // UPDATE customer by id
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.toCustomer(customerDTO);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(CustomerMapper.toCustomerDTO(updatedCustomer));
    }

    // DELETE customer by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
