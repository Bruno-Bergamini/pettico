package com.pucpr.pettico.products.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pucpr.pettico.products.ProductCategories;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;
    private String name;
    private Float price;
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public ProductCategories getCategory() {
        return category;
    }

    public void setCategory(ProductCategories category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    private ProductCategories category;

    public Integer getId() {
        return id;
    }

}
