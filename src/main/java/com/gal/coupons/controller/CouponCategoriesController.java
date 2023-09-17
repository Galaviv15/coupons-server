package com.gal.coupons.controller;

import com.gal.coupons.dto.CategoryDTO;
import com.gal.coupons.entities.CouponCategory;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.CouponCategoriesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CouponCategoriesController {

    private CouponCategoriesLogic couponCategoriesLogic;
    @Autowired
    public CouponCategoriesController(CouponCategoriesLogic couponCategoriesLogic) {
        this.couponCategoriesLogic = couponCategoriesLogic;
    }

    //http://localhost:8080/categories
    @PostMapping
    public void createCategory(@RequestBody CouponCategory category) throws ServerException{
        couponCategoriesLogic.createCategory(category);
    }

    //http://localhost:8080/categories/id
    @GetMapping("{categoryId}")
    public CategoryDTO getCategory(@PathVariable("categoryId") int categoryId) throws ServerException{
        return couponCategoriesLogic.getCategoryById(categoryId);
    }

    //http://localhost:8080/categories/id
    @GetMapping("/byType")
    public CategoryDTO getCategoryByType(@RequestParam("categoryType") String categoryType) throws ServerException{
        return couponCategoriesLogic.getCategoryByType(categoryType);
    }

    //http://localhost:8080/categories/byPage?pageNumber=
    @GetMapping
    public List<CategoryDTO> getAllCategories() throws ServerException{
        return couponCategoriesLogic.getAllCategories();
    }

    //http://localhost:8080/categories/
    @PutMapping
    public void updateCategory(@RequestBody CouponCategory category) throws ServerException{
        couponCategoriesLogic.updateCategory(category);
    }

    //http://localhost:8080/categories/id
    @DeleteMapping("{categoryId}")
    public void removeCategory(@PathVariable ("categoryId") int categoryId) throws ServerException {
        couponCategoriesLogic.removeCategory(categoryId);
    }
}
