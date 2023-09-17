package com.gal.coupons.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;
    @Column(name = "ADDRESS" , nullable = false)
    private String address;
    @Column(name = "PHONE_NUMBER" , nullable = false, unique = true)
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
    private List<Purchase> purchases;


    public Customer() {
    }

    public Customer(int id, User user, String address, String phoneNumber) {
        this.id = user.getId();
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }


}
