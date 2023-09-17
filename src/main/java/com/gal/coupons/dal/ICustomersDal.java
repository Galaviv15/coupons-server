package com.gal.coupons.dal;

import com.gal.coupons.dto.CustomerDTO;
import com.gal.coupons.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICustomersDal extends CrudRepository <Customer, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.CustomerDTO (c.user.userName, c.address, c.phoneNumber) FROM Customer c" +
            " WHERE c.user.id= :customerId")
    CustomerDTO findCustomerById(@Param("customerId")int customerId);

    @Query("SELECT NEW com.gal.coupons.dto.CustomerDTO (c.user.userName, c.address, c.phoneNumber) FROM Customer c")
    List<CustomerDTO> findAllCustomers();

//    @Query("UPDATE Customer c SET c.user.userName= :userName, c.address= :address, c.phoneNumber= :phoneNumber " +
//            "WHERE c.user.id= :id")
//    void updateCustomerDetails(@Param("userName") String userName, @Param("address") String address,
//                               @Param("phoneNumber") String phoneNumber, @Param("id") int id);


}
