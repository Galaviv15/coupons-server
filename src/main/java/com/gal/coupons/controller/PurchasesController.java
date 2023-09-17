package com.gal.coupons.controller;

import com.gal.coupons.dto.PurchaseDTO;
import com.gal.coupons.entities.Purchase;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.PurchasesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    private PurchasesLogic purchasesLogic;

    @Autowired
    public PurchasesController(PurchasesLogic purchasesLogic) {
        this.purchasesLogic = purchasesLogic;
    }

    //http://localhost:8080/purchases
    @PostMapping
    public void createPurchase(@RequestHeader String authorization, @RequestBody Purchase purchase) throws ServerException{
        purchasesLogic.createPurchase(authorization, purchase);
    }

    //http://localhost:8080/purchases/id
    @GetMapping("{purchaseId}")
    public PurchaseDTO getPurchase(@PathVariable("purchaseId") int purchaseId) throws ServerException{
        return purchasesLogic.getPurchase(purchaseId);
    }

    //http://localhost:8080/purchases
    @GetMapping
    public List<PurchaseDTO> getAllPurchases() throws ServerException{
        return purchasesLogic.getAllPurchases();
    }

    //http://localhost:8080/purchases/byCustomerId?customerId=?
    @GetMapping("/byCustomerId")
    public List<PurchaseDTO> findAllPurchasesByCustomerId(@RequestParam("customerId") int customerId) throws ServerException{
        return purchasesLogic.findAllPurchasesByCustomerId(customerId);
    }

    //http://localhost:8080/purchases/byCouponId?couponId=?
    @GetMapping("/byCouponId")
    public List<PurchaseDTO> findAllPurchasesByCouponId(@RequestParam("couponId") int couponId) throws ServerException{
        return purchasesLogic.findAllPurchasesByCouponId(couponId);
    }

    //http://localhost:8080/purchases
//    @PutMapping
//    public void updatePurchase(@RequestBody Purchase purchase) throws ServerException{
//        purchasesLogic.updatePurchase(purchase);
//    }

    //http://localhost:8080/purchases/id
    @DeleteMapping("{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") int purchaseId) throws ServerException {
        purchasesLogic.removePurchase(purchaseId);
    }
}
