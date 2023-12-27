package com.example.backend.dao;

import com.example.backend.entity.Comment;
import com.example.backend.entity.CommentDemo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    @Select("select * from comment")
    public List<Comment> getCommentList();

    @Select("select count(*) from comment where comment_question_id=#{id}")
    public int getCommentNumByQuestion(String id);

    @Select("select * from comment where comment_id=#{id}")
    public Comment getCommentById(String id);

    @Select("select comment_id,comment_question_id,user_name as comment_user_name," +
            "comment_time,comment_content,comment_like,comment_dislike " +
            "from comment join user on comment_user_id=user_id " +
            "where comment_question_id=#{id} order by comment_time")
    public List<CommentDemo> getCommentListByQuestionOrderByTime(String id);

    @Select("select comment_id,comment_question_id,user_name as comment_user_name," +
            "comment_time,comment_content,comment_like,comment_dislike " +
            "from comment join user on comment_user_id=user_id " +
            "where comment_question_id=#{id} order by comment_like")
    public List<CommentDemo> getCommentListByQuestionOrderByLike(String id);

    @Insert("insert into comment(comment_question_id,comment_user_id,comment_time,comment_content) " +
            "values(#{comment_question_id},#{comment_user_id},#{comment_time},#{comment_content})")
    public int insertComment(Comment comment);

    @Update("update comment set comment_like=#{like} where comment_id=#{id}")
    public int updateCommentLike(int like,String id);

    @Update("update comment set comment_dislike=#{dislike} where comment_id=#{id}")
    public int updateCommentDislike(int dislike,String id);

    @Delete("delete from comment where comment_id=#{id}")
    public int deleteComment(String id);
}
