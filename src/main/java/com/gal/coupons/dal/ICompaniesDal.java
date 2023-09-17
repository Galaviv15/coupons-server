package com.gal.coupons.dal;

import com.gal.coupons.dto.CompanyDTO;
import com.gal.coupons.entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICompaniesDal extends CrudRepository<Company, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.CompanyDTO (c.id, c.name, c.address, c.phoneNumber) FROM Company c " +
            " WHERE c.id= :companyId")
    CompanyDTO findCompanyById(@Param("companyId") int companyId);

    @Query("SELECT NEW com.gal.coupons.dto.CompanyDTO (c.id, c.name, c.address, c.phoneNumber) FROM Company c " +
            " WHERE c.name= :companyName")
    CompanyDTO findCompanyByName(@Param("companyName") String companyName);

    @Query("SELECT NEW com.gal.coupons.dto.CompanyDTO (c.id, c.name, c.address, c.phoneNumber) FROM Company c")
    List<CompanyDTO> findAllCompanies();


}
