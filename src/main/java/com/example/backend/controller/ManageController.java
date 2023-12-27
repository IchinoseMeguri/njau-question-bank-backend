package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.service.KnowledgeService;
import com.example.backend.service.QuestionService;
import com.example.backend.service.SubjectService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    UserService userService;

    @Autowired
    KnowledgeService knowledgeService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    QuestionService questionService;

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUserList();
    }

    @RequestMapping("/getQuestions")
    public List<QuestionDemo> getQuestions(){
        List<Question> questions=questionService.getQuestionList();
        List<QuestionDemo> res=new ArrayList<>();
        for (Question q:questions) {
            QuestionDemo demo=new QuestionDemo();
            demo.setQuestion_id(q.getQuestion_id());
            Knowledge k= knowledgeService.getKnowledgeById(q.getKnowledge_id());
            demo.setKnowledge_id(q.getKnowledge_id());
            demo.setKnowledge_name(k.getKnowledge_name());
            demo.setSubject_id(k.getSubject_id());
            demo.setSubject_name(subjectService.getSubjectNameById(k.getSubject_id()));
            demo.setQuestion_type(q.getQuestion_type());
            demo.setQuestion_from(q.getQuestion_from());
            demo.setQuestion_score(q.getQuestion_score());
            demo.setQuestion_level(q.getQuestion_level());
            demo.setQuestion_description(q.getQuestion_description());
            demo.setQuestion_answer(q.getQuestion_answer());
            demo.setQuestion_answer_detail(q.getQuestion_answer_detail());
            res.add(demo);
        }
        return res;
    }

    @RequestMapping("/updateUser")
    public int updateUser(String userId,String username,String userType){
        User user=userService.getUserByName(username);
        if(user!=null) {
            if(!user.getUser_id().equals(userId))
                return -1;
        }
        User u=userService.getUserById(userId);
        u.setUser_name(username);
        u.setUser_type(userType);
        return userService.updateUser(u);
    }

    @RequestMapping("/resetPwd")
    public int resetPwd(String userId){
        return userService.updatePassword(userId,"123456");
    }

    @RequestMapping("/deleteUser")
    public int deleteUser(String userId){
        return userService.deleteUser(userId);
    }

    @RequestMapping("/addUser")
    public int addUser(String username,String userType){
        User user=userService.getUserByName(username);
        if(user!=null) return -1;
        User u=new User();
        u.setUser_name(username);
        u.setUser_type(userType);
        u.setUser_password("123456");
        return userService.insertUser(u);
    }

    @RequestMapping("/getSubjects")
    public List<Subject> getSubjects(){
        return subjectService.getSubjectList();
    }

    @RequestMapping("/getKnowledge")
    public List<Knowledge> getKnowledge(){
        return knowledgeService.getKnowledgeList();
    }

    @RequestMapping("/updateQuestion")
    public int updateQuestion(String questionId,String knowledgeId,int type,int score,String from,
                              int level,String description,String answer,String answerDetail){
        Question question= questionService.getQuestionById(questionId);
        question.setQuestion_type(type);
        question.setQuestion_score(score);
        question.setQuestion_description(description);
        question.setQuestion_level(level);
        question.setKnowledge_id(knowledgeId);
        question.setQuestion_from(from);
        question.setQuestion_answer(answer);
        question.setQuestion_answer_detail(answerDetail);
        return questionService.updateQuestion(question);
    }

    @RequestMapping("/deleteQuestion")
    public int deleteQuestion(String questionId){
        return questionService.deleteQuestion(questionId);
    }

    @RequestMapping("/addQuestion")
    public int addQuestion(String knowledgeId,int type,int score,String from,
                           int level,String description,String answer,String answerDetail) {
        Question question = new Question();
        question.setQuestion_type(type);
        question.setQuestion_score(score);
        question.setQuestion_description(description);
        question.setQuestion_level(level);
        question.setKnowledge_id(knowledgeId);
        question.setQuestion_from(from);
        question.setQuestion_answer(answer);
        question.setQuestion_answer_detail(answerDetail);
        return questionService.insertQuestion(question);
    }
}
