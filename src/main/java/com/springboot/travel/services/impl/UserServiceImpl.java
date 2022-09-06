package com.springboot.travel.services.impl;

import com.springboot.travel.dao.UserMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.UserService;
import com.springboot.travel.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public List<User> selectUserPageList(int page, int size) {
        return userMapper.selectUserPageList((page-1)*size , size);
    }

    @Override
    public void editUser(User user) throws SpringbootException{
        User user1 = userMapper.selectByPrimaryKey(user.getUserId());
        user.setCreateTime(user1.getCreateTime());
        int count = userMapper.updateByPrimaryKey(user);
        if (count == 0) {
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_USER_ERROR);
        }
    }

    @Override
    public User selectUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void deleteUser(int userId) throws SpringbootException {
        int count = userMapper.deleteByPrimaryKey(userId);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public List<User> selectUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void deleteAll(int[] userId) {
        for (int i = 0 ; i < userId.length ; i ++){
            userMapper.deleteByPrimaryKey(userId[i]);
        }
    }

    @Override
    public User selectByTelephone(String telephone) {
        return userMapper.selectByTelephone(telephone);
    }

    @Override
    public void userRegister(User user) throws SpringbootException {
        user.setCreateTime(new Date());
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(user.getPassword(), salt);
        user.setPassword(md5);
        user.setSalt(salt);
        user.setState(0);
        user.setUsername("张三");
        user.setAge(0);
        user.setEmail("2222@qq.com");
        int count = userMapper.insertSelective(user);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void updateUser(User user) throws SpringbootException {
        int count = userMapper.updateByPrimaryKey(user);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_ERROR);
        }
    }

    @Override
    public void userLogin(String telephone, String password) throws SpringbootException {
        User user = userMapper.selectByTelephone(telephone);
        if (StringUtils.isEmpty(user)){
            throw new SpringbootException(SpringbootExceptionEnum.NEED_NO_EXISTED);
        }
        if (user.getState() == 0){
            throw new SpringbootException(SpringbootExceptionEnum.NEED_ADMIN_PASS);
        }
        String md5 = MD5Utils.md5Digest(password , user.getSalt());
        if (!user.getPassword().equals(md5)){
            throw new SpringbootException(SpringbootExceptionEnum.PASSWORD_ERROR);
        }
    }
}
