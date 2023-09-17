package com.gal.coupons.dto;

public class SuccessfulLoginResponse {

    private String token;


    public SuccessfulLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SuccessfulLoginResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
