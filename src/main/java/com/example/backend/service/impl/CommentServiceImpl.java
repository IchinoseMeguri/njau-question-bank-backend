package com.example.backend.service.impl;

import com.example.backend.dao.CommentDao;
import com.example.backend.entity.Comment;
import com.example.backend.entity.CommentDemo;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;
    public List<Comment> getCommentList(){
        return commentDao.getCommentList();
    }

    public int getCommentNumByQuestion(String id){
        return commentDao.getCommentNumByQuestion(id);
    }

    public Comment getCommentById(String id){
        return commentDao.getCommentById(id);
    }

    public List<CommentDemo> getCommentListByQuestionOrderByTime(String id){
        return commentDao.getCommentListByQuestionOrderByTime(id);
    }

    public List<CommentDemo> getCommentListByQuestionOrderByLike(String id){
        return commentDao.getCommentListByQuestionOrderByLike(id);
    }

    public int insertComment(Comment comment){
        return commentDao.insertComment(comment);
    }

    public int updateCommentLike(int like,String id){
        return commentDao.updateCommentLike(like, id);
    }

    public int updateCommentDislike(int dislike,String id){
        return commentDao.updateCommentDislike(dislike, id);
    }

    public int deleteComment(String id){
        return commentDao.deleteComment(id);
    }
}
