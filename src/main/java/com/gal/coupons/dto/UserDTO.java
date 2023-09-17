package com.gal.coupons.dto;

public class UserDTO {

    private int id;
    private String userName;
    private String userType;
    private String companyName;

    public UserDTO() {
    }

    public UserDTO(int id, String userName, String userType, String companyName) {
        this.id = id;
        this.userName = userName;
        this.userType = userType;
        this.companyName = companyName;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
