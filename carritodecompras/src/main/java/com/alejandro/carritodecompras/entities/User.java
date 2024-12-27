package com.alejandro.carritodecompras.entities;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

// To specific the name of the table in mysql
// In mysql the name of this table is 'user' but in this project 
// the name of this class is 'User'
@Entity
@Table(name = "user")
public class User {

    // Mapping of class attributes with table fields in mysql

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String name;

    @NotBlank // To obligate to this attribute not to empty or blank values.
    private String lastname;

    // To set a relationship one to many
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_user")
    private List<PurchaseHistory> purchases;

    public User() {
        this.purchases = new ArrayList<>();
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonIgnore // To not send the information about 'purchases'
    public List<PurchaseHistory> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchaseHistory> purchases) {
        this.purchases = purchases;
    }
}
