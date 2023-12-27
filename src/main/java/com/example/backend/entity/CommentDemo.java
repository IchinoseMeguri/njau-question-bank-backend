package com.example.backend.entity;

import java.time.LocalDateTime;

public class CommentDemo {
    private String comment_id;
    private String comment_question_id;
    private String comment_user_name;
    private LocalDateTime comment_time;
    private String comment_content;
    private int comment_like;
    private int comment_dislike;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_question_id() {
        return comment_question_id;
    }

    public void setComment_question_id(String comment_question_id) {
        this.comment_question_id = comment_question_id;
    }

    public String getComment_user_name() {
        return comment_user_name;
    }

    public void setComment_user_name(String comment_user_name) {
        this.comment_user_name = comment_user_name;
    }

    public LocalDateTime getComment_time() {
        return comment_time;
    }

    public void setComment_time(LocalDateTime comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getComment_like() {
        return comment_like;
    }

    public void setComment_like(int comment_like) {
        this.comment_like = comment_like;
    }

    public int getComment_dislike() {
        return comment_dislike;
    }

    public void setComment_dislike(int comment_dislike) {
        this.comment_dislike = comment_dislike;
    }
}
