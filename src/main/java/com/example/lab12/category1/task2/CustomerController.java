package com.example.lab12.category1.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());

        return repository.save(existingCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Customer customer = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
        repository.delete(customer);
        return ResponseEntity.ok().build();
    }
}