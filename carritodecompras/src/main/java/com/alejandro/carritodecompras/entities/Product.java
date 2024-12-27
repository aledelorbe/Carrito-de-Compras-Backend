package com.alejandro.carritodecompras.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// To specific the name of the table in mysql
// In mysql the name of this table is 'product' but in this project 
// the name of this class is 'Product'
@Entity
@Table(name = "product") 
public class Product {

    // Mapping of class attributes with table fields in mysql

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String name;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String description;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String brand;
    
    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String category;
    
    @NotNull // To obligate to this attribute not to empty
    @Min(1) // To obligate to this attribute to contains a value equal to or greater than one
    private Double price;
    
    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String image;
    
    private Long status;

    public Product() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
