package com.gal.coupons.controller;

import com.gal.coupons.dto.CustomerDTO;
import com.gal.coupons.entities.Customer;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.CustomersLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomersLogic customersLogic;

    @Autowired
    public CustomerController(CustomersLogic customersLogic) {
        this.customersLogic = customersLogic;
    }

    //http://localhost:8080/customers
    @PostMapping
    public void createCustomer(@RequestBody Customer customer) throws ServerException {
        customer.getUser().setUserType("CUSTOMER");
        customersLogic.createCustomer(customer);
    }

    //http://localhost:8080/customers/id
    @GetMapping("{customerId}")
    public CustomerDTO getCustomer(@PathVariable("customerId") int customerId) throws ServerException{
        return customersLogic.getCustomerById(customerId);
    }

    //http://localhost:8080/customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() throws ServerException{
        return customersLogic.getAllCustomers();
    }

    //http://localhost:8080/customers
    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) throws ServerException{
        customer.getUser().setUserType("CUSTOMER");
        customersLogic.updateCustomer(customer);
    }

//    //http://localhost:8080/customers
//    @PutMapping("/updateDetails")
//    public void updateCustomerDetails(@RequestBody Customer customer) throws ServerException{
//        customer.getUser().setUserType("CUSTOMER");
//        customersLogic.updateCustomerDetails(customer);
//    }

    //http://localhost:8080/customers/id
    @DeleteMapping("{customerId}")
    public void removeCustomer(@PathVariable("customerId") int customerId) throws ServerException{
        customersLogic.removeCustomer(customerId);
    }

}
