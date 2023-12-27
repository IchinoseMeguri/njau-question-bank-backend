package com.example.backend.dao;

import com.example.backend.entity.QuestionDemo;
import com.example.backend.entity.Wrong;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WrongDao {
    @Select("select * from wrong")
    public List<Wrong> getWrongList();

    @Select("select * from wrong where wrong_user_id=#{id}")
    public List<Wrong> getWrongListByUser(String id);

    @Select("select * from wrong where wrong_user_id=#{userId} and wrong_question_id=#{questionId}")
    public Wrong getWrong(String userId,String questionId);

    @Select("select * from wrong,subject,knowledge,question where " +
            "subject.subject_id=knowledge.subject_id and " +
            "knowledge.knowledge_id=question.knowledge_id and " +
            "question.question_id=wrong.wrong_question_id and " +
            "wrong.wrong_user_id=#{id} order by " +
            "subject.subject_id,knowledge.knowledge_id")
    public List<QuestionDemo> getWrongQuestionsDetailByUser(String id);

    @Select("select * from wrong,subject,knowledge,question where " +
            "subject.subject_id=knowledge.subject_id and " +
            "knowledge.knowledge_id=question.knowledge_id and " +
            "question.question_id=wrong.wrong_question_id and " +
            "wrong.wrong_user_id=#{id} and " +
            "question.question_description like #{str} order by " +
            "subject.subject_id,knowledge.knowledge_id")
    public List<QuestionDemo> searchWrong(String id,String str);

    @Select("select count(*) from wrong where wrong_user_id=#{id}")
    public int getUserWrongNum(String id);

    @Insert("insert into wrong values(#{wrong_user_id},#{wrong_question_id})")
    public int insertWrong(Wrong wrong);

    @Delete("delete from wrong where wrong_user_id=#{wrong_user_id} " +
            "and wrong_question_id=#{wrong_question_id}")
    public int deleteWrong(Wrong wrong);
}
