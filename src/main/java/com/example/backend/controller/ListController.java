package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.service.KnowledgeService;
import com.example.backend.service.QuestionService;
import com.example.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    KnowledgeService knowledgeService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/getKnowledgeList",method = RequestMethod.GET)
    public List<SubjectDemo> getKnowledgeList(){
        List<SubjectDemo> subjects=new ArrayList<>();
        List<Subject> subjectList=subjectService.getSubjectList();
        for (Subject s:subjectList) {
            int id=s.getSubject_id();
            String name=s.getSubject_name();
            SubjectDemo subjectDemo=new SubjectDemo(id,name);
            List<Knowledge> knowledgeList=knowledgeService.getKnowledgeListBySubject(id);
            for (Knowledge k:knowledgeList) {
                String k_id=k.getKnowledge_id();
                String k_name=k.getKnowledge_name();
                int q_num=questionService.getQuestionNumByKnowledge(Integer.parseInt(k_id));
                subjectDemo.addKnowledgeToList(new KnowledgeDemo(k_id,k_name,q_num));
            }
            subjects.add(subjectDemo);
        }
        System.out.println("getKnowledgeList");
        return subjects;
    }

    @RequestMapping(value = "/getQuestionList",method = RequestMethod.GET)
    public List<Question> getQuestionList(int knowledgeId){
        return questionService.getQuestionListByKnowledge(knowledgeId);
    }
}
