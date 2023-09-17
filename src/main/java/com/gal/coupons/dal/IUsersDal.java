package com.gal.coupons.dal;


import com.gal.coupons.dto.UserData;
import com.gal.coupons.dto.UserDTO;
import com.gal.coupons.dto.UserLoginData;
import com.gal.coupons.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface IUsersDal extends CrudRepository<User, Integer> {

    @Query("SELECT NEW com.gal.coupons.dto.UserDTO (u.id, u.userName, u.userType, c.name) FROM User u JOIN Company c " +
            "ON c.id=u.company.id WHERE u.id= :userId")
    UserDTO findUserById(@Param("userId")int userId);

    @Query("SELECT NEW com.gal.coupons.entities.User (u.id, u.userName, u.userType) FROM User u WHERE u.userName= :userName")
    User findUserByUserName(@Param("userName") String userName);

    @Query("SELECT NEW com.gal.coupons.dto.UserDTO (u.id, u.userName, u.userType, c.name) FROM User u JOIN Company c " +
            "ON u.company.id=c.id")
    List<UserDTO> findAllUsers ();

    @Query("SELECT NEW com.gal.coupons.dto.UserDTO (u.id, u.userName, u.userType, c.name) FROM User u JOIN Company c " +
            "ON u.company.id=c.id WHERE c.id= :companyId")
    List<UserDTO> findAllUsersByCompanyId (@Param("companyId")int companyId);

    @Query("SELECT NEW com.gal.coupons.dto.UserDTO (u.id, u.userName, u.userType, c.name) FROM User u JOIN Company c " +
            "ON u.company.id=c.id WHERE c.name= :companyName")
    List<UserDTO> findAllUsersByCompanyName (@Param("companyName") String companyName);

    @Query("SELECT NEW com.gal.coupons.dto.UserDTO (u.id, u.userName, u.userType, c.name) FROM User u JOIN Company c " +
            "ON u.company.id=c.id WHERE u.userType= :userType")
    List<UserDTO> findAllUsersByUsertype (@Param("userType")String userType);

    @Query("SELECT NEW com.gal.coupons.dto.UserData (u.id, u.userType, u.userName, u.company.id) FROM User u " +
            "WHERE u.userName= :userName AND u.password= :password")
    UserData login (@Param("userName") String userName, @Param("password") String password);

}
