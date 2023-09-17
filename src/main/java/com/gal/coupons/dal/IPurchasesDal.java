package com.gal.coupons.dal;

import com.gal.coupons.dto.PurchaseDTO;
import com.gal.coupons.entities.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IPurchasesDal extends CrudRepository <Purchase, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.PurchaseDTO (pur.id, cus.user.userName, cus.address, coup.name, coup.price, " +
            "pur.amountPurchased) FROM Purchase pur " +
            "JOIN Customer cus ON pur.customer.id=cus.user.id " +
            "Join Coupon coup ON pur.coupon.id=coup.id " +
            "WHERE pur.id= :purchaseId")
    PurchaseDTO findPurchaseById(@Param("purchaseId") int purchaseId);

    @Query("SELECT NEW com.gal.coupons.dto.PurchaseDTO (pur.id, cus.user.userName, cus.address, coup.name, coup.price, " +
            "pur.amountPurchased) FROM Purchase pur " +
            "JOIN Customer cus ON pur.customer.id=cus.user.id " +
            "Join Coupon coup ON pur.coupon.id=coup.id")
    List<PurchaseDTO> findAllPurchases();

    @Query("SELECT NEW com.gal.coupons.dto.PurchaseDTO (pur.id, cus.user.userName, cus.address, coup.name, coup.price, " +
            "pur.amountPurchased) FROM Purchase pur " +
            "JOIN Customer cus ON pur.customer.id=cus.user.id " +
            "Join Coupon coup ON pur.coupon.id=coup.id " +
            "WHERE cus.user.id= :customerId")
    List<PurchaseDTO> findAllPurchasesByCustomerId (@Param("customerId") int customerId);

    @Query("SELECT NEW com.gal.coupons.dto.PurchaseDTO (pur.id, cus.user.userName, cus.address, coup.name, coup.price, " +
            "pur.amountPurchased) FROM Purchase pur " +
            "JOIN Customer cus ON pur.customer.id=cus.user.id " +
            "Join Coupon coup ON pur.coupon.id=coup.id " +
            "WHERE coup.id= :couponId")
    List<PurchaseDTO> findAllPurchasesByCouponId (@Param("couponId") int couponId);

}
