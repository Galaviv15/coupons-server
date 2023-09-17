package com.gal.coupons.dto;

public class UserData {

    private int id;
    private String userType;
    private String userName;
    private Integer companyId;


    public UserData() {
    }

    public UserData(int id, String userType, String userName, Integer companyId) {
        this.id = id;
        this.userType = userType;
        this.userName = userName;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", userName='" + userName + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
