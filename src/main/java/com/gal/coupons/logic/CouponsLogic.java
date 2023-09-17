package com.gal.coupons.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.coupons.consts.Consts;
import com.gal.coupons.dal.ICouponsDal;
import com.gal.coupons.dto.CouponBasicDTO;
import com.gal.coupons.dto.CouponDTO;
import com.gal.coupons.dto.CouponForEditDTO;
import com.gal.coupons.dto.UserData;
import com.gal.coupons.entities.Coupon;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponsLogic {

    private ICouponsDal couponsDal;

    @Autowired
    private CompaniesLogic companiesLogic;

    @Autowired
    private CouponCategoriesLogic couponCategoriesLogic;

    @Autowired
    public CouponsLogic(ICouponsDal couponsDal) {
        this.couponsDal = couponsDal;
    }

    public void createCoupon(String authorization, Coupon coupon) throws ServerException{
        validateCouponCreation(coupon);
        UserData userData = null;
        try {
            //getting the user details in order to validate user type
            userData = getUserDetailsByToken(authorization);
        } catch (JsonProcessingException e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
        //If user type isn't company, he is not allowed to create coupon
        if (userData.getUserType().equals("COMPANY")){
            throw new ServerException(ErrorType.UNAUTHORIZED_USER);
        }
        try {
            couponsDal.save(coupon);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CouponDTO getCoupon(int couponId) throws ServerException{
        validateCouponExistence(couponId);
        try {
            return couponsDal.findCouponById(couponId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CouponForEditDTO findCouponByIdForEdit(int couponId) throws ServerException{
        validateCouponExistence(couponId);
        try {
            return couponsDal.findCouponByIdForEdit(couponId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }


    public List<CouponDTO> getAllCoupons(int pageNumber) throws ServerException{
        Pageable paging = PageRequest.of(pageNumber, Consts.COUPONS_PER_PAGE);
        try {
            return couponsDal.findAllCoupons(paging);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> findAllCouponsNoPagination() throws ServerException{
        try {
            return couponsDal.findAllCouponsNoPagination();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> getAllCouponsByCompanyId(int companyId) throws ServerException{
        companiesLogic.validateCompanyExistenceByID(companyId);
        try {
            return couponsDal.findCouponsByCompanyId(companyId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> getAllCompanyCouponsByCompanyName(String companyName) throws ServerException{
        companiesLogic.validateCompanyExistenceByName(companyName);
        try {
            return couponsDal.findAllCouponsByCompanyName(companyName);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> getCouponsInPriceRange(int minPrice, int maxPrice) throws ServerException{
        validatePriceRange(minPrice, maxPrice);
        try {
            return couponsDal.findByPriceBetween(minPrice, maxPrice);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> getAllCouponsByCategoryType(String categoryType) throws ServerException{
        couponCategoriesLogic.validateCategoryExistenceByType(categoryType);
        try {
            return couponsDal.findAllCouponsByCategoryType(categoryType);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponDTO> getAllCouponsByCategoryId(int categoryId) throws ServerException{
        couponCategoriesLogic.validateCategoryExistenceById(categoryId);
        try {
            return couponsDal.findAllCouponsByCategoryId(categoryId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }



    public void updateCoupon(Coupon coupon) throws ServerException{
        validateCouponCreation(coupon);
        try {
            couponsDal.save(coupon);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCoupon(int couponId) throws ServerException{
        validateCouponExistence(couponId);
        try {
            couponsDal.deleteById(couponId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CouponBasicDTO> findAllCouponsBasicOnlyName() throws ServerException{
        return couponsDal.findAllCouponsBasicOnlyName();
    }

    public void removeExpiredCoupons(Date todayDate){
        couponsDal.deleteExpiredCoupons(todayDate);
    }


    void validateCouponExistence(int couponId) throws ServerException{
        if (!couponsDal.existsById(couponId)){
            throw new ServerException(ErrorType.COUPON_DOES_NOT_EXIST);
        }
    }

    private void validateCouponCreation(Coupon coupon) throws ServerException{
        validateCouponPrice(coupon.getPrice());
        validateCouponName(coupon.getName());
        validateDescription(coupon.getDescription());
        validateDates(coupon.getStartDate(), coupon.getEndDate());
        companiesLogic.validateCompanyExistenceByID(coupon.getCompany().getId());
        couponCategoriesLogic.validateCategoryExistenceById(coupon.getCategory().getId());
    }

    private void validatePriceRange(int minPrice, int maxPrice) throws ServerException{
        if (minPrice < 0){
            throw new ServerException(ErrorType.INVALID_PRICE);
        }
        if (maxPrice < 0){
            throw new ServerException(ErrorType.INVALID_PRICE);
        }
        if (minPrice > maxPrice){
            throw new ServerException(ErrorType.INVALID_PRICE_RANGE);
        }
    }

    private void validateCouponPrice (int price) throws ServerException{
        if (price <= 0){
            throw new ServerException(ErrorType.INVALID_PRICE);
        }
    }

    private void validateCouponName(String name) throws ServerException{
        if (name.length() < 3) {
            throw new ServerException(ErrorType.INVALID_COUPON_NAME);
        }

        if (name.length() > 25) {
            throw new ServerException(ErrorType.INVALID_COUPON_NAME);
        }
    }

    private void validateDescription(String description) throws ServerException{
        if (description.length() > 255) {
            throw new ServerException(ErrorType.INVALID_COUPON_DESCRIPTION);
        }
    }

    private void validateDates(Date startDate, Date endDate) throws ServerException{
        if (startDate == null) {
            throw new ServerException(ErrorType.INVALID_DATE);
        }

        if (endDate == null) {
            throw new ServerException(ErrorType.INVALID_DATE);
        }
    }

    //Function to get user data in order to check the userType and set companyID in the coupon
    private UserData getUserDetailsByToken(String authorization) throws JsonProcessingException {
        Claims userDecryptedData = JWTUtils.decodeJWT(authorization);
        ObjectMapper objectMapper = new ObjectMapper();
        UserData userData = objectMapper.readValue(userDecryptedData.getSubject(), UserData.class);
        return userData;
    }

}
