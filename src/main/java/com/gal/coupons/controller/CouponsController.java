package com.gal.coupons.controller;

import com.gal.coupons.dto.CouponBasicDTO;
import com.gal.coupons.dto.CouponDTO;
import com.gal.coupons.dto.CouponForEditDTO;
import com.gal.coupons.entities.Coupon;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }

    //http://localhost:8080/coupons
    @PostMapping
    public void createCoupon(@RequestHeader String authorization, @RequestBody Coupon coupon) throws ServerException {
        couponsLogic.createCoupon(authorization, coupon);
    }

    //http://localhost:8080/coupons/id
    @GetMapping("{couponId}")
    public CouponDTO getCoupon(@PathVariable("couponId") int couponId) throws ServerException {
        return couponsLogic.getCoupon(couponId);
    }

    //http://localhost:8080/coupons/forEdit?couponId=?
    @GetMapping("/forEdit")
    public CouponForEditDTO findCouponByIdForEdit(@RequestParam("couponId") int couponId) throws ServerException {
        return couponsLogic.findCouponByIdForEdit(couponId);
    }


        //http://localhost:8080/coupons/byPage?pageNumber=?
    @GetMapping("/byPage")
    public List<CouponDTO> getAllCoupons(@RequestParam("pageNumber") int pageNumber) throws ServerException {
        return couponsLogic.getAllCoupons(pageNumber);
    }

    //http://localhost:8080/coupons/
    @GetMapping
    public List<CouponDTO> findAllCouponsNoPagination() throws ServerException {
        return couponsLogic.findAllCouponsNoPagination();
    }

    //http://localhost:8080/coupons/byCompanyId?companyId=?
    @GetMapping("/byCompanyId")
    public List<CouponDTO> getAllCompanyCouponsByCompanyId(@RequestParam("companyId") int companyId) throws ServerException {
        return couponsLogic.getAllCouponsByCompanyId(companyId);
    }

    //http://localhost:8080/coupons/byCompanyName?companyName=?
    @GetMapping("/byCompanyName")
    public List<CouponDTO> getAllCompanyCouponsByCompanyName(@RequestParam("companyName") String companyName) throws ServerException {
        return couponsLogic.getAllCompanyCouponsByCompanyName(companyName);
    }

    //http://localhost:8080/coupons/byPriceRange?minPrice=?&maxPrice=?
    @GetMapping("/byPriceRange")
    public List<CouponDTO> getCouponsInPriceRange(@RequestParam("minPrice") int minPrice,
                                                  @RequestParam("maxPrice") int maxPrice) throws ServerException {
        return couponsLogic.getCouponsInPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/byCategoryType")
    public List<CouponDTO> getAllCouponsByCategoryType(@RequestParam("categoryType") String categoryType) throws ServerException {
        return couponsLogic.getAllCouponsByCategoryType(categoryType);
    }

    @GetMapping("/byCategoryId")
    public List<CouponDTO> getAllCouponsByCategoryId(@RequestParam("categoryId") int categoryId) throws ServerException {
        return couponsLogic.getAllCouponsByCategoryId(categoryId);
    }

    @GetMapping("/basicDetailed")
    public List<CouponBasicDTO> findAllCouponsBasicOnlyName() throws ServerException {
        return couponsLogic.findAllCouponsBasicOnlyName();
    }

    //http://localhost:8080/coupons
    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon) throws ServerException {
        couponsLogic.updateCoupon(coupon);
    }

    //http://localhost:8080/coupons/id
    @DeleteMapping("{couponId}")
    public void removeCoupon(@PathVariable("couponId") int couponId) throws ServerException {
        couponsLogic.removeCoupon(couponId);
    }

    //http://localhost:8080/coupons/removeExpired?todayDate=YEAR/MONTH/DAY
//    @DeleteMapping("/removeExpired")
//    public void removeExpiredCoupons(@RequestParam("todayDate") Date todayDate){
//        couponsLogic.removeExpiredCoupons(todayDate);
//    }


}
