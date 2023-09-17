package com.gal.coupons.dto;

public class CouponBasicDTO {

    private int id;
    private String couponName;
    private int price;

    public CouponBasicDTO(int id, String couponName, int price) {
        this.id = id;
        this.couponName = couponName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CouponBasicDTO{" +
                "id=" + id +
                ", couponName='" + couponName + '\'' +
                ", price=" + price +
                '}';
    }
}
