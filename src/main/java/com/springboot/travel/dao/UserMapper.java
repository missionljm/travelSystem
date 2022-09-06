package com.springboot.travel.dao;

import com.springboot.travel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserList();

    List<User> selectUserPageList(int page , int size);

    List<User> selectByUsername(String username);

    User selectByTelephone(String telephone);
}