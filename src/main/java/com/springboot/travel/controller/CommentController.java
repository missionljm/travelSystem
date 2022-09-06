package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Comment;
import com.springboot.travel.pojo.Guide;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询评论列表（管理员）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("commentList.do/{page}/{size}")
    @ResponseBody
    public Map commentList(@PathVariable("page") int page , @PathVariable("size") int size){
        List<Comment> commentList = commentService.selectAll();
        List<Comment> commentPageList = commentService.selectPageComment(page , size);
        int pageSize = commentList.size();
        Map commentMap = new HashMap();
        commentMap.put("code", 1000);
        commentMap.put("massage", "请求成功");
        commentMap.put("title", pageSize);
        commentMap.put("commentPageList", commentPageList);
        return commentMap;
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @PostMapping("deleteComment.do/{commentId}")
    @ResponseBody
    public ApiRestResponse deleteComment(@PathVariable("commentId") Integer commentId){
        if (commentId == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.DELETE_ERROR);
        }
        try {
            commentService.deleteCommentById(commentId);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 批删除功能
     * @param comments
     * @return
     */
    @PostMapping("deleteAllComment.do")
    @ResponseBody
    public ApiRestResponse deleteAllComment(@RequestBody Comment[] comments){
        try {
            int[] commentId = new int[comments.length];
            for (int i = 0 ; i < comments.length ; i ++){
                commentId[i] = comments[i].getCommentId();
                commentService.deleteCommentById(commentId[i]);
            }
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 通过景点查询景点信息8
     * @param spotId1
     * @return
     */
    @PostMapping("selectCommentBySpotId.do/{scenicSpotId}")
    @ResponseBody
    public Map selectCommentBySpotId(@PathVariable("scenicSpotId") String spotId1){
        Map commentMap = new HashMap();
        List<Comment> commentList;
        if (spotId1.equals("null")) {
            commentList = commentService.selectAll();
        } else {
            int spotId = Integer.valueOf(spotId1);
            commentList = commentService.selectCommentBySpotId(spotId);
        }
        commentMap.put("code", 10001);
        commentMap.put("msg", "查询成功");
        commentMap.put("commentList", commentList);
        return commentMap;
    }

    /**
     * 查询所有用户评论
     * @return
     */
    @GetMapping("selectUserComment.do")
    @ResponseBody
    public Map selectCommentList(){
        Map commentMap = new HashMap();
        List<Comment> commentList = commentService.selectAll();
        commentMap.put("status", 10001);
        commentMap.put("msg", "查询成功");
        commentMap.put("commentList", commentList);
        return commentMap;
    }

    @PutMapping("insertComment.do/{content}")
    @ResponseBody
    public ApiRestResponse insertComment(HttpSession session , @PathVariable("content") String content){
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        int userId = user.getUserId();
        String headPortrait = user.getHeadPortrait();
        comment.setContent(content);
        comment.setHeadPortrait(headPortrait);
        comment.setUserId(userId);
        comment.setScenicSpotId(1);
        try {
            commentService.insertComment(comment);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

}
