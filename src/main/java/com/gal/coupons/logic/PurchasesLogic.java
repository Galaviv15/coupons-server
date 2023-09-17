package com.gal.coupons.logic;

import com.gal.coupons.dal.IPurchasesDal;
import com.gal.coupons.dto.PurchaseDTO;
import com.gal.coupons.entities.Customer;
import com.gal.coupons.entities.Purchase;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasesLogic {

    private IPurchasesDal purchasesDal;

    @Autowired
    private CouponsLogic couponsLogic;

    @Autowired
    private CustomersLogic customersLogic;

    @Autowired
    public PurchasesLogic(IPurchasesDal purchasesDal) {
        this.purchasesDal = purchasesDal;
    }

    public void createPurchase(String authorization, Purchase purchase) throws ServerException {
        int customerId = JWTUtils.validateToken(authorization);
        validateAmountOfCouponPurchased(purchase.getAmountPurchased());
        Customer customer = customersLogic.findById(customerId);
        purchase.setCustomer(customer);
        try {
            purchasesDal.save(purchase);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public PurchaseDTO getPurchase(int purchaseId) throws ServerException {
        validatePurchaseExistence(purchaseId);
        try {
            return purchasesDal.findPurchaseById(purchaseId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDTO> getAllPurchases() throws ServerException {
        try {
            return purchasesDal.findAllPurchases();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDTO> findAllPurchasesByCustomerId(int customerId) throws ServerException {
        customersLogic.validateCustomerExistence(customerId);
        try {
            return purchasesDal.findAllPurchasesByCustomerId(customerId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<PurchaseDTO> findAllPurchasesByCouponId(int couponId) throws ServerException {
        couponsLogic.validateCouponExistence(couponId);
        try {
            return purchasesDal.findAllPurchasesByCouponId(couponId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }


//    public void updatePurchase(Purchase purchase) throws ServerException {
//        validateAmountOfCouponPurchased(purchase.getAmountPurchased());
//        customersLogic.validateCustomerExistence(purchase.getCustomer().getId());
//        couponsLogic.validateCouponExistence(purchase.getCoupon().getId());
//        try {
//            purchasesDal.save(purchase);
//        } catch (Exception e) {
//            throw new ServerException(ErrorType.GENERAL_ERROR);
//        }
//    }

    public void removePurchase(int purchaseId) throws ServerException {
        validatePurchaseExistence(purchaseId);
        try {
            purchasesDal.deleteById(purchaseId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }


    private void validatePurchaseExistence(int purchaseId) throws ServerException {
        if (!purchasesDal.existsById(purchaseId)) {
            throw new ServerException(ErrorType.PURCHASE_DOES_NOT_EXIST);
        }
    }

    private void validateAmountOfCouponPurchased(int amountPurchased) throws ServerException {
        if (amountPurchased <= 0 || amountPurchased > 5) {
            throw new ServerException(ErrorType.INVALID_AMOUNT_OF_COUPON_PURCHASED);
        }
    }
}
