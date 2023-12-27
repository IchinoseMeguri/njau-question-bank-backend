package com.example.backend.service;

import com.example.backend.entity.Comment;
import com.example.backend.entity.CommentDemo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public List<Comment> getCommentList();

    public int getCommentNumByQuestion(String id);

    public Comment getCommentById(String id);

    public List<CommentDemo> getCommentListByQuestionOrderByTime(String id);

    public List<CommentDemo> getCommentListByQuestionOrderByLike(String id);

    public int insertComment(Comment comment);

    public int updateCommentLike(int like,String id);

    public int updateCommentDislike(int dislike,String id);

    public int deleteComment(String id);
}
