package com.patrick.assignment.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Product {

    @CsvBindByName(column = "productId")
    private @Id
    Long id;

    @CsvBindByName(column = "productName")
    private String name;

    @CsvBindByName(column = "productManufacturingCity")
    private String manufacturingCity;

    public Product(){

    }

    public Product(Long id, String name, String manufacturingCity) {
        this.id = id;
        this.name = name;
        this.manufacturingCity = manufacturingCity;
    }

    public Product(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturingCity() {
        return manufacturingCity;
    }

    public void setManufacturingCity(String manufacturingCity) {
        this.manufacturingCity = manufacturingCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturingCity='" + manufacturingCity + '\'' +
                '}';
    }
}
