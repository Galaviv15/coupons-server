package com.gal.coupons.dal;

import com.gal.coupons.dto.CouponBasicDTO;
import com.gal.coupons.dto.CouponDTO;
import com.gal.coupons.dto.CouponForEditDTO;
import com.gal.coupons.entities.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


public interface ICouponsDal extends CrudRepository<Coupon, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, " +
            "coup.price, coup.description, coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup " +
            "JOIN Company comp ON coup.company.id = comp.id WHERE coup.id= :couponId")
    CouponDTO findCouponById(@Param("couponId") int couponId);

    @Query("SELECT NEW com.gal.coupons.dto.CouponForEditDTO (coup.id, coup.name, coup.category.id, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id WHERE coup.id= :couponId")
    CouponForEditDTO findCouponByIdForEdit(@Param("couponId") int couponId);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id")
    List<CouponDTO> findAllCoupons(Pageable paging);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id")
    List<CouponDTO> findAllCouponsNoPagination();

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id WHERE comp.id= :companyId")
    List<CouponDTO> findCouponsByCompanyId(@Param("companyId") int companyId);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id WHERE comp.name= :companyName")
    List<CouponDTO> findAllCouponsByCompanyName(@Param("companyName") String companyName);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id" +
            " WHERE coup.category.type= :categoryType")
    List<CouponDTO> findAllCouponsByCategoryType(@Param("categoryType") String categoryType);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id" +
            " WHERE coup.category.id= :categoryId")
    List<CouponDTO> findAllCouponsByCategoryId(@Param("categoryId") int categoryId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Coupon coup WHERE coup.endDate< :todayDate")
    void deleteExpiredCoupons(@Param("todayDate") Date todayDate);

    @Query("SELECT NEW com.gal.coupons.dto.CouponDTO (coup.id, coup.name, coup.category.type, coup.category.id, comp.name, comp.id, coup.price, coup.description, " +
            "coup.startDate, coup.endDate, coup.imgURL) FROM Coupon coup JOIN Company comp ON coup.company.id = comp.id WHERE coup.price>= :minPrice And" +
            " coup.price<= :maxPrice")
    List<CouponDTO> findByPriceBetween(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

    @Query("SELECT NEW com.gal.coupons.dto.CouponBasicDTO (coup.id, coup.name, coup.price) FROM Coupon coup")
    List<CouponBasicDTO> findAllCouponsBasicOnlyName();
}
