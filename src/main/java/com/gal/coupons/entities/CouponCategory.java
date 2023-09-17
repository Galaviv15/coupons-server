package com.gal.coupons.entities;

import javax.persistence.*;


@Entity
@Table(name = "coupon_categories")
public class CouponCategory {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "TYPE", nullable = false)
    private String type;


    public CouponCategory() {
    }

    public CouponCategory(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public CouponCategory(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
