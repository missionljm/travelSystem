package com.springboot.travel.services;


import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.User;

import java.util.List;

public interface UserService {

    List<User> selectUserList();

    List<User> selectUserPageList(int page , int size);

    void editUser(User user) throws SpringbootException;

    User selectUserById(int userId);

    void deleteUser(int userId) throws SpringbootException ;

    List<User> selectUserByUsername(String username);

    void deleteAll(int[] userId);

    void userLogin(String telephone , String password) throws SpringbootException;

    User selectByTelephone(String telephone);

    void userRegister(User user)throws SpringbootException;

    void updateUser(User user) throws SpringbootException;
}
