package com.gal.coupons.controller;

import com.gal.coupons.dto.SuccessfulLoginResponse;
import com.gal.coupons.dto.UserData;
import com.gal.coupons.dto.UserDTO;
import com.gal.coupons.dto.UserLoginData;
import com.gal.coupons.entities.User;
import com.gal.coupons.exceptions.ServerException;
import com.gal.coupons.logic.UsersLogic;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Filter;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersLogic usersLogic;

    @Autowired
    public UsersController(UsersLogic usersLogic) {
        this.usersLogic = usersLogic;
    }

    //http://localhost:8080/users
    @PostMapping
    public void createUser(@RequestBody User user) throws ServerException {
        usersLogic.createUser(user);
    }

    //http://localhost:8080/users/id
    @GetMapping("{userId}")
    public UserDTO getUser(@PathVariable("userId") int userId) throws ServerException {
        return usersLogic.getUser(userId);
    }

    //http://localhost:8080/users/byPage?pageNumber=?
    @GetMapping
    public List<UserDTO> getAllUsers() throws ServerException {
        return usersLogic.getAllUsers();
    }

    //http://localhost:8080/users/byCompanyId?companyId=?
    @GetMapping("/byCompanyId")
    public List<UserDTO> getAllUsersByCompanyId(@RequestParam("companyId") int companyId) throws ServerException {
        return usersLogic.getAllUsersByCompanyId(companyId);
    }

    //http://localhost:8080/users/byCompanyName?companyName=?
    @GetMapping("/byCompanyName")
    public List<UserDTO> findAllUsersByCompanyName(@RequestParam("companyName") String companyName) throws ServerException {
        return usersLogic.getAllUsersByCompanyName(companyName);
    }

    @GetMapping("/byUserType")
    public List<UserDTO> getAllUsersByUsertype(@RequestParam("userType") String userType) throws ServerException {
        return usersLogic.getAllUsersByUsertype(userType);
    }

    //http://localhost:8080/users
    @PutMapping
    public void updateUser(@RequestBody User user) throws ServerException {
        usersLogic.updateUser(user);
    }

    //http://localhost:8080/users/id
    @DeleteMapping("{userId}")
    public void removeUser(@PathVariable("userId") int userId) throws ServerException {
        usersLogic.removeUser(userId);
    }

    //http://localhost:8080/users/login
    @PostMapping("/login")
    public SuccessfulLoginResponse login(@RequestBody UserLoginData userLoginData) throws ServerException {
        return usersLogic.login(userLoginData);
    }

    //check if I get the exact details of user made login
    @GetMapping("/decodeCheck")
    public UserData decodeCheck () throws ServerException{
       return usersLogic.decodeCheck();
    }
}
