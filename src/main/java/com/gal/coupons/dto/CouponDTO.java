package com.gal.coupons.dto;

import java.util.Date;

public class CouponDTO {

    private int id;
    private String name;
    private String categoryType;
    private int categoryID;
    private String companyName;
    private int companyID;
    private int price;
    private String description;
    private Date startDate;
    private Date endDate;
    private String imgURL;

    public CouponDTO() {
    }

    public CouponDTO(int id, String name, String categoryType, int categoryID, String companyName, int companyID, int price, String description, Date startDate, Date endDate, String imgURL) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
        this.categoryID = categoryID;
        this.companyName = companyName;
        this.companyID = companyID;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "CouponDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", categoryID=" + categoryID +
                ", companyName='" + companyName + '\'' +
                ", companyID=" + companyID +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }
}