package com.alejandro.carritodecompras.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

// To specific the name of the table in mysql
// In mysql the name of this table is 'detailed_purchase_history' but in this project 
// the name of this class is 'DetailedPurchaseHistory'
@Entity
@Table(name = "detailed_purchase_history")
public class DetailedPurchaseHistory {

    // Mapping of class attributes with table fields in mysql

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detail")
    private Long id;

    @NotNull // To obligate to this attribute not to empty
    private Long quantity;

    // this attribute can be an empty value because it's calculate in 'addDetailProduct' method
    private Double total;

    // To create the relationship many to one
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    public DetailedPurchaseHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
