package com.springboot.travel.services.impl;

import com.springboot.travel.dao.AdminMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Admin;
import com.springboot.travel.services.AdminService;
import com.springboot.travel.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void adminLogin(int adminId, String password) throws SpringbootException {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        if (admin == null){
            throw new SpringbootException(SpringbootExceptionEnum.NEED_NO_EXISTED);
        }
        String md5 = MD5Utils.md5Digest(password , admin.getSalt());
        if (!admin.getPassword().equals(md5)){
            throw new SpringbootException(SpringbootExceptionEnum.PASSWORD_ERROR);
        }
    }

    @Override
    public Admin selectAdminById(int adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void checkRegister(Admin admin) throws SpringbootException {
        Admin admin1 = adminMapper.selectByName(admin.getUsername());
        if (!StringUtils.isEmpty(admin1)){
            throw new SpringbootException(SpringbootExceptionEnum.NAME_EXISTED);
        }
        Admin admin2 = new Admin();
        admin2.setCreateTime(new Date());
        admin2.setUsername(admin.getUsername());
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(admin.getPassword() , salt);
        admin2.setPassword(md5);
        admin2.setAdminPortrait("http://localhost:8081/head/head.png");
        admin2.setSalt(salt);
        adminMapper.insertSelective(admin2);
    }
}
