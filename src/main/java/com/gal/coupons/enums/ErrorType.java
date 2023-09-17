package com.gal.coupons.enums;

public enum ErrorType {

    GENERAL_ERROR(601, "General error", true),
    USER_DOES_NOT_EXIST(604, "User does not exist", false),
    PURCHASE_DOES_NOT_EXIST(605, "Purchase does not exist", false),
    COMPANY_NAME_ALREADY_EXIST(606, "Company name already exist", false),
    INVALID_NULL_USER_NAME(607, "Username cannot be null", false),
    TOO_WEAK_PASSWORD(608, "Password must contain at least 1 special char(!,@,#,$), 1 capital letter and 1 digit", false),
    INVALID_PASSWORD_LENGTH(609, "Password must contain at least 6 chars and no more than 12 chars", false),
    USERNAME_ALREADY_EXIST(610, "Username already exist", false),
    INVALID_USER_TYPE(611, "User type must be: CUSTOMER/ADMIN/COMPANY", false),
    INVALID_PRICE_RANGE(612,"Maximum price must be higher than minimum price, and both have to be positive", false),
    CUSTOMER_DOES_NOT_EXIST(613, "Customer does not exist", false),
    USER_NAME_OR_PASSWORD_INCORRECT(614, "Username or Password are incorrect", false),
    INVALID_COMPANY_PHONE_NUMBER(615, "Phone number must contains digits, max one *, and max two -", false),
    INVALID_ADDRESS(616, "Address must contain at least 6 chars and no more than 30 chars", false),
    INVALID_COMPANY_NAME(617, "Company name must contain at least 3 chars and no more than 25 chars", false),
    INVALID_COUPON_CATEGORY_NAME(618, "Coupon category must contain at least 3 chars and no more than 25 chars", false),
    INVALID_COUPON_NAME(619, "Coupon name must contain at least 3 chars and no more than 25 chars", false),
    INVALID_PRICE(620, "Coupon price must be positive", false),
    CREATION_ERROR(621, "Creation error", false),
    DELETION_ERROR(622, "Deletion error", false),
    UPDATE_ERROR(623, "Update Error", false),
    READ_ERROR(624, "Read error", false),
    COPY_FAILED(625, "Copy table Error", false),
    INVALID_COUPON_DESCRIPTION(626, "Coupon description must contain no more than 200 chars", false),
    INVALID_DATE(627, "Date cannot be null", false),
    CHECK_ERROR(628,"Check Error", false),
    INVALID_PHONE_NUMBER_LENGTH(629, "Phone number must contains at least 5 chars and no more than 12 chars", false),
    INVALID_AMOUNT_OF_COUPON_PURCHASED(630,"Amount purchased must be positive and Maximum of 3 coupons allowed in single purchase", false),
    COUPON_DOES_NOT_EXIST(631,"Coupon does not Exist", false),
    COMPANY_DOES_NOT_EXIST(632,"Company does not Exist", false),
    PHONE_NUMBER_ALREADY_EXIST(633, "Phone number already exist", false),
    CATEGORY_DOES_NOT_EXIST(634, "Category does not exist", false),
    COUPON_NAME_ALREADY_EXIST(635, "Coupon name already exist", false),
    COUPON_CATEGORY_DOES_NOT_EXIST(636, "Coupon category does not exist", false),
    INVALID_PASSWORD_NULL(637, "Password cannot be Null", false),
    INVALID_USER_NAME_LENGTH(638, "User name must contain at least 4 chars and no more than 12 chars", false),
    INVALID_LOGIN_DETAILS(639, "Username or password are incorrect", false),
    INVALID_CUSTOMER_PHONE_NUMBER(640, "Phone number must contains digits, and max two '-'", false),
    LOGIN_FAILURE(641, "Username or password are incorrect", false),
    INVALID_CUSTOMER_TYPE(642, "When creating Customer, user type must be 'CUSTOMER' !", false),
    UNAUTHORIZED_USER(643, "Only COMPANY user is allowed to create a coupon !", false);









    private int errorNumber;
    private String errorMessage;
    private boolean isShowStackTrace;

    ErrorType(int errorNumber, String errorMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    ErrorType(int errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }


    @Override
    public String toString() {
        return "ErrorType{" +
                "errorNumber=" + errorNumber +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
