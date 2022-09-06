package com.springboot.travel.services.impl;

import com.springboot.travel.dao.CommentMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Comment;
import com.springboot.travel.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }

    @Override
    public List<Comment> selectPageComment(int page, int size) {
        return commentMapper.selectPageComment((page-1)*size , size);
    }

    @Override
    public Comment selectById(int commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public List<Comment> selectCommentBySpotId(int spotId) {
        return commentMapper.selectCommentBySpotId(spotId);
    }

    @Override
    public void deleteCommentById(int commentId) throws SpringbootException {
        int count = commentMapper.deleteByPrimaryKey(commentId);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public void insertComment(Comment comment) throws SpringbootException {
        comment.setCreateTime(new Date());
        int count = commentMapper.insertSelective(comment);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void editComment(Comment comment) throws SpringbootException {
        int count = commentMapper.updateByPrimaryKeySelective(comment);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_ERROR);
        }
    }
}
