package com.gal.coupons.logic;

import com.gal.coupons.dal.ICustomersDal;
import com.gal.coupons.dto.CustomerDTO;
import com.gal.coupons.entities.Customer;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.utils.HashPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersLogic {

    private ICustomersDal customersDal;

    @Autowired
    private UsersLogic usersLogic;

    @Autowired
    public CustomersLogic(ICustomersDal customersDal) {
        this.customersDal = customersDal;
    }


    public void createCustomer(Customer customer) throws ServerException {
        validateCustomerCreation(customer);
        try {
            //Encrypting the password before sending it to the DB//
            String hashedPassword = HashPasswordUtils.convertPasswordToHash(customer.getUser().getPassword());
            customer.getUser().setPassword(hashedPassword);
            customersDal.save(customer);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public CustomerDTO getCustomerById(int customerId) throws ServerException {
        validateCustomerExistence(customerId);
        try {
            return customersDal.findCustomerById(customerId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public Customer findById(int customerId) throws ServerException {
        try {
            return customersDal.findById(customerId).get();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<CustomerDTO> getAllCustomers() throws ServerException {
        try {
            return customersDal.findAllCustomers();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void updateCustomer(Customer customer) throws ServerException {
        //validateCustomerCreation(customer);
        customer.getUser().setPassword("BLAblas!1");
        try {
            customersDal.save(customer);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void removeCustomer(int customerId) throws ServerException {
        validateCustomerExistence(customerId);
        try {
            customersDal.deleteById(customerId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

//    public void updateCustomerDetails(Customer customer) throws ServerException {
//        customer.getUser().setPassword("Check1!");
//        try {
//            customersDal.updateCustomerDetails(customer.getUser().getUserName(), customer.getAddress(), customer.getPhoneNumber(), customer.getUser().getId());
//        } catch (Exception e) {
//            throw new ServerException(ErrorType.GENERAL_ERROR);
//        }
//    }



    void validateCustomerExistence(int customerId) throws ServerException {
        if (!customersDal.existsById(customerId)) {
            throw new ServerException(ErrorType.CUSTOMER_DOES_NOT_EXIST);
        }
    }

    private void validateCustomerCreation(Customer customer) throws ServerException {
        validateAddress(customer.getAddress());
        validateCustomerPhoneNumber(customer.getPhoneNumber());
        usersLogic.validatePassword(customer.getUser().getPassword());
        usersLogic.validateUserName(customer.getUser());
    }

    private void validateCustomerPhoneNumber(String phoneNumber) throws ServerException {
        int counterOfHyphens = 0;
        for (int index = 0; index < phoneNumber.length(); index++) {
            if (phoneNumber.charAt(index) == '-') {
                counterOfHyphens++;
            }
            if (phoneNumber.charAt(index) < '0' && phoneNumber.charAt(index) > '9'
                    && phoneNumber.charAt(index) != '-') {
                throw new ServerException(ErrorType.INVALID_CUSTOMER_PHONE_NUMBER);
            }
            if (counterOfHyphens > 2) {
                throw new ServerException(ErrorType.INVALID_CUSTOMER_PHONE_NUMBER);
            }
        }
    }

    private void validateAddress(String address) throws ServerException {
        if (address.length() < 6) {
            throw new ServerException(ErrorType.INVALID_ADDRESS);
        }

        if (address.length() > 30) {
            throw new ServerException(ErrorType.INVALID_ADDRESS);
        }
    }


}
