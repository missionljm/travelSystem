package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectAll();

    List<Comment> selectPageComment(int page , int size);

    Comment selectById(int commentId);

    List<Comment> selectCommentBySpotId(int spotId);

    void deleteCommentById(int commentId) throws SpringbootException;

    void insertComment(Comment comment) throws  SpringbootException;

    void editComment(Comment comment) throws SpringbootException;
}
