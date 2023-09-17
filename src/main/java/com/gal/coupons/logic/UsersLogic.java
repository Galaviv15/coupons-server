package com.gal.coupons.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gal.coupons.dal.IUsersDal;
import com.gal.coupons.dto.SuccessfulLoginResponse;
import com.gal.coupons.dto.UserData;
import com.gal.coupons.dto.UserDTO;
import com.gal.coupons.dto.UserLoginData;
import com.gal.coupons.entities.User;
import com.gal.coupons.enums.ErrorType;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.utils.HashPasswordUtils;
import com.gal.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersLogic {

    private IUsersDal usersDal;

    @Autowired
    private CompaniesLogic companiesLogic;

    @Autowired
    public UsersLogic(IUsersDal usersDal) {
        this.usersDal = usersDal;
    }


    public void createUser(User user) throws ServerException {
        validateUserCreation(user);
        try {
            //Encrypting the password before sending it to the DB//
            String hashedPassword = HashPasswordUtils.convertPasswordToHash(user.getPassword());
            user.setPassword(hashedPassword);
            usersDal.save(user);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public UserDTO getUser(int userId) throws ServerException {
        validateUserExistence(userId);
        try {
            return usersDal.findUserById(userId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<UserDTO> getAllUsers() throws ServerException {
        try {
            return usersDal.findAllUsers();
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<UserDTO> getAllUsersByCompanyId(int companyId) throws ServerException {
        companiesLogic.validateCompanyExistenceByID(companyId);
        try {
            return usersDal.findAllUsersByCompanyId(companyId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<UserDTO> getAllUsersByCompanyName(String companyName) throws ServerException {
        companiesLogic.validateCompanyExistenceByName(companyName);
        try {
            return usersDal.findAllUsersByCompanyName(companyName);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public List<UserDTO> getAllUsersByUsertype(String userType) throws ServerException {
        validateUserType(userType);
        try {
            return usersDal.findAllUsersByUsertype(userType);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void updateUser(User user) throws ServerException {
        validateUserCreation(user);
        try {
            usersDal.save(user);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public void removeUser(int userId) throws ServerException {
        validateUserExistence(userId);
        try {
            usersDal.deleteById(userId);
        } catch (Exception e) {
            throw new ServerException(ErrorType.GENERAL_ERROR);
        }
    }

    public SuccessfulLoginResponse login(UserLoginData userLoginData) throws ServerException {
        //Converting the password before sending it as the password on the login//
        String hashedPassword = HashPasswordUtils.convertPasswordToHash(userLoginData.getPassword());
        UserData userData = usersDal.login(userLoginData.getUserName(), hashedPassword);
        if (userData == null) {
            throw new ServerException(ErrorType.LOGIN_FAILURE);
        }

        String token = null;
        try {
            token = JWTUtils.createJWT(userData);
        } catch (JsonProcessingException e) {
            throw new ServerException(ErrorType.LOGIN_FAILURE);
        }

        SuccessfulLoginResponse successfulLoginResponse = new SuccessfulLoginResponse(token);
        return successfulLoginResponse;
    }

    //check decode
    public UserData decodeCheck() throws ServerException{
        UserLoginData userLoginData = new UserLoginData("Customer1", "Customer1!");
        SuccessfulLoginResponse successfulLoginResponse = login(userLoginData);
        String token = successfulLoginResponse.getToken();

        Claims userData = JWTUtils.decodeJWT(token);
        ObjectMapper objectMapper = new ObjectMapper();
        UserData user = new UserData();
        try {
            user = objectMapper.readValue(userData.getSubject(), UserData.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    void validatePassword(String password) throws ServerException {
        boolean isContainsCapitalLetter = false;
        boolean isContainsLowerCase = false;
        boolean isContainsSpecialCharOrNumber = false;

        if (password == null) {
            throw new ServerException(ErrorType.INVALID_PASSWORD_NULL);
        }

//        if (password.length() < 6 || password.length() > 12) {
//            throw new ServerException(ErrorType.INVALID_PASSWORD_LENGTH);
//        }

        for (int index = 0; index < password.length(); index++) {
            if (password.charAt(index) >= 'A' && password.charAt(index) <= 'Z') {
                isContainsCapitalLetter = true;
            }

            if (password.charAt(index) >= 'a' && password.charAt(index) <= 'z') {
                isContainsLowerCase = true;
            }

            if (password.charAt(index) == '!' || password.charAt(index) == '@' || password.charAt(index) == '#' ||
                    password.charAt(index) == '$') {
                isContainsSpecialCharOrNumber = true;
            }

            if (password.charAt(index) >= '0' && password.charAt(index) <= '9') {
                isContainsSpecialCharOrNumber = true;
            }
        }

        if (!isContainsCapitalLetter || !isContainsSpecialCharOrNumber || !isContainsLowerCase) {
            throw new ServerException(ErrorType.TOO_WEAK_PASSWORD);
        }
    }

    void validateUserName(User user) throws ServerException {
        if (user.getUserName() == null) {
            throw new ServerException(ErrorType.INVALID_NULL_USER_NAME);
        }
        if (user.getUserName().length() < 4 || user.getUserName().length() > 12) {
            throw new ServerException(ErrorType.INVALID_USER_NAME_LENGTH);
        }
        if (usersDal.findUserByUserName(user.getUserName()) != null &&
                usersDal.findUserByUserName(user.getUserName()).getId() != user.getId()) {
            throw new ServerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
    }

    private void validateUserCreation(User user) throws ServerException{
        validatePassword(user.getPassword());
        validateUserType(user.getUserType());
        validateUserName(user);
        if(!user.getUserType().equals("ADMIN")){
            companiesLogic.validateCompanyExistenceByID(user.getCompany().getId());
        }
    }
    private void validateUserExistence(int userId) throws ServerException {
        if (!usersDal.existsById(userId)) {
            throw new ServerException(ErrorType.USER_DOES_NOT_EXIST);
        }
    }

    private void validateUserType(String userType) throws ServerException {
        if (!userType.equals("CUSTOMER") && !userType.equals("COMPANY") && !userType.equals("ADMIN")) {
            throw new ServerException(ErrorType.INVALID_USER_TYPE);
        }
    }

//    private void validateLoginFailure(UserLoginData userLoginData) throws ServerException {
//        if (usersDal.login(userLoginData) == null) {
//            throw new ServerException(ErrorType.INVALID_LOGIN_DETAILS);
//        }
//    }


}


