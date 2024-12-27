package com.alejandro.carritodecompras.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

// To specific the name of the table in mysql
// In mysql the name of this table is 'purchase_history' but in this project 
// the name of this class is 'PurchaseHistory'
@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

    // Mapping of class attributes with table fields in mysql

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase_history")
    private Long id;

    // the attributes: firstImage, total and date can be empty because they are
    // calculate
    // in the 'addPurchase' method.
    @Column(name = "first_image")
    private String firstImage;

    private Double total;

    private LocalDate date;

    // To set a relationship one to many
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_purchase_history")
    private List<DetailedPurchaseHistory> details;

    public PurchaseHistory() {
        this.details = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonIgnore // To not send the information about 'details'
    public List<DetailedPurchaseHistory> getDetails() {
        return details;
    }

    public void setDetails(List<DetailedPurchaseHistory> details) {
        this.details = details;
    }

    // To set the date when the record is saved in the db 
    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }
}
