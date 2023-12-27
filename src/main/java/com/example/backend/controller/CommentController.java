package com.example.backend.controller;

import com.example.backend.entity.Comment;
import com.example.backend.entity.CommentDemo;
import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/like",method = RequestMethod.GET)
    public int likeComment(String commentId){
        Comment comment=commentService.getCommentById(commentId);
        int like=comment.getComment_like()+1;
        commentService.updateCommentLike(like,commentId);
        return like;
    }

    @RequestMapping(value = "/dislike",method = RequestMethod.GET)
    public int dislikeComment(String commentId){
        Comment comment=commentService.getCommentById(commentId);
        int dislike=comment.getComment_dislike()+1;
        commentService.updateCommentDislike(dislike,commentId);
        return dislike;
    }

    @RequestMapping(value = "/insertComment",method = RequestMethod.GET)
    public List<CommentDemo> insertComment(String userId,String questionId,String content){
        Comment comment=new Comment();
        comment.setComment_user_id(userId);
        comment.setComment_question_id(questionId);
        comment.setComment_content(content);
        comment.setComment_time(LocalDateTime.now());
        commentService.insertComment(comment);
        return commentService.getCommentListByQuestionOrderByTime(questionId);
    }
}
