package com.springboot.travel.dao;

import com.springboot.travel.pojo.Comment;
import com.springboot.travel.pojo.Guide;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();

    List<Comment> selectPageComment(int page , int size);

    List<Comment> selectCommentBySpotId(int spotId);
}