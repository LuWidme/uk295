package com.example.demo.domain.product;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class Product {
    @NotNull
    @Id
    private int id;
    private int useless;
    private String description;
    @Min(0)
    private double price;

    public Product() {
    }

    public Product(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
