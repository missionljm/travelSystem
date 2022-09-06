package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.Admin;

public interface AdminService {

    void adminLogin(int adminId , String password) throws SpringbootException;

    Admin selectAdminById(int adminId);

    void checkRegister(Admin admin) throws SpringbootException;
}
