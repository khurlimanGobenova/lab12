package com.example.lab12.category1.task3;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double amount;

    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();

}