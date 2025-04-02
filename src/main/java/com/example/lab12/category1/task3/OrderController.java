package com.example.lab12.category1.task3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository repository;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return repository.save(order);
    }
    @GetMapping
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));

        existingOrder.setDescription(order.getDescription());
        existingOrder.setAmount(order.getAmount());

        return repository.save(existingOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Order order = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Not Found"));
        repository.delete(order);
        return ResponseEntity.ok().build();
    }
}