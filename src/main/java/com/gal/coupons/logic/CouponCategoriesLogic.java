package com.gal.coupons.logic;

import com.gal.coupons.dal.ICouponCategoriesDal;
import com.gal.coupons.dto.CategoryDTO;
import com.gal.coupons.entities.CouponCategory;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponCategoriesLogic {

    private ICouponCategoriesDal couponCategoriesDal;

    @Autowired
    public CouponCategoriesLogic(ICouponCategoriesDal couponCategoriesDal){
        this.couponCategoriesDal= couponCategoriesDal;
    }

    public void createCategory(CouponCategory couponCategory) throws ServerException{
        validateCategoryType(couponCategory.getType());
        try {
            couponCategoriesDal.save(couponCategory);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CategoryDTO getCategoryById(int categoryId) throws ServerException{
        validateCategoryExistenceById(categoryId);
        try {
            return couponCategoriesDal.findCategoryById(categoryId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CategoryDTO getCategoryByType(String categoryType) throws ServerException{
        validateCategoryExistenceByType(categoryType);
        try {
            return couponCategoriesDal.findCategoryByType(categoryType);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CategoryDTO> getAllCategories() throws ServerException{
        try {
            return couponCategoriesDal.findAllCategories();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCategory(CouponCategory couponCategory) throws ServerException{
        validateCategoryType(couponCategory.getType());
        try {
            couponCategoriesDal.save(couponCategory);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCategory(int categoryId) throws ServerException{
        validateCategoryExistenceById(categoryId);
        try {
            couponCategoriesDal.deleteById(categoryId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    void validateCategoryExistenceById(int categoryId) throws ServerException{
        if (!couponCategoriesDal.existsById(categoryId)){
            throw new ServerException(ErrorType.CATEGORY_DOES_NOT_EXIST);
        }
    }

    void validateCategoryExistenceByType (String type) throws ServerException{
        if (couponCategoriesDal.findCategoryByType(type) == null){
            throw new ServerException(ErrorType.CATEGORY_DOES_NOT_EXIST);
        }
    }

    private void validateCategoryType(String type) throws ServerException{
        if (type.length() < 3) {
            throw new ServerException(ErrorType.INVALID_COUPON_CATEGORY_NAME);
        }
        if (type.length() > 25) {
            throw new ServerException(ErrorType.INVALID_COUPON_CATEGORY_NAME);
        }
    }

}
