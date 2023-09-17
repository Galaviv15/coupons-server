package com.gal.coupons.dto;

public class PurchaseDTO {

    private int id;
    private String userName;
    private String address;
    private String couponName;
    private int couponPrice;
    private int amountPurchased;

    public PurchaseDTO() {
    }

    public PurchaseDTO(int id, String userName, String address, String couponName, int couponPrice, int amountPurchased) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.couponName = couponName;
        this.couponPrice = couponPrice;
        this.amountPurchased = amountPurchased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(int couponPrice) {
        this.couponPrice = couponPrice;
    }

    public int getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(int amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
                "userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponPrice=" + couponPrice +
                ", amountPurchased=" + amountPurchased +
                '}';
    }
}
