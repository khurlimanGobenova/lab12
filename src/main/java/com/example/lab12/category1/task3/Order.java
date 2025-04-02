package com.example.lab12.category1.task3;

import jakarta.persistence.*;
@Entity
public class Order {
    private Long productId;
    private String description;
    private Double amount;
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description=description;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
