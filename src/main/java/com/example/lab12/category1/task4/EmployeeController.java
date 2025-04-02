package com.example.lab12.category1.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employees createEmployee(@RequestBody Employees employee) {
        return repository.save(employee);
    }

    @GetMapping
    public List<Employees> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employees getEmployeeById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
    }

    @PutMapping("/{id}")
    public Employees updateEmployee(@PathVariable Long id, @RequestBody Employees employee) {
        Employees existingEmployee = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        return repository.save(existingEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Employees employee = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
        repository.delete(employee);
        return ResponseEntity.ok().build();
    }
}