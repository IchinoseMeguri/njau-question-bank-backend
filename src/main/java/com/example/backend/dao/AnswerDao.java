package com.example.backend.dao;

import com.example.backend.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerDao {
    @Select("select * from answer")
    public List<Answer> getAnswerList();

    @Select("select * from answer where answer_user_id=#{userId} " +
            "and answer_question_id=#{questionId}")
    public Answer getAnswerByUserAndQuestion(String userId,String questionId);

    @Select("select count(*) from answer where answer_user_id=#{userId}")
    public int getUserAnswerNum(String userId);

    @Select("select answer_content from answer where answer_user_id=#{userId} " +
            "and answer_question_id=#{questionId}")
    public String getAnswerContentByUserAndQuestion(String userId,String questionId);

    @Insert("insert into answer values(#{answer_user_id},#{answer_question_id},#{answer_content},#{answer_score})")
    public int insertAnswer(Answer answer);

    @Update("update answer set answer_content=#{answer},answer_score=#{score} " +
            "where answer_user_id=#{userId} and answer_question_id=#{questionId}")
    public int updateAnswer(String userId,String questionId,String answer,int score);
}
