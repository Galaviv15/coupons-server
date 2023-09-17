package com.gal.coupons.dal;

import com.gal.coupons.dto.CategoryDTO;
import com.gal.coupons.entities.CouponCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICouponCategoriesDal extends CrudRepository <CouponCategory, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.CategoryDTO (c.id, c.type) FROM CouponCategory c WHERE c.id= :categoryId")
    CategoryDTO findCategoryById(@Param("categoryId") int categoryId);

    @Query("SELECT NEW com.gal.coupons.dto.CategoryDTO (c.id, c.type) FROM CouponCategory c WHERE c.type= :categoryType")
    CategoryDTO findCategoryByType(@Param("categoryType") String categoryType);

    @Query("SELECT NEW com.gal.coupons.dto.CategoryDTO (c.id, c.type) FROM CouponCategory c")
    List<CategoryDTO> findAllCategories();

}
