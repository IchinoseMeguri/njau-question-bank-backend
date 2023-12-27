package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @Autowired
    QuestionService questionService;

    @Autowired
    KnowledgeService knowledgeService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/getCollectList",method = RequestMethod.GET)
    public List<SubjectDemo> getWrongList(String userId){
        List<SubjectDemo> res=new ArrayList<>();
        List<QuestionDemo> collects=collectService.getCollectQuestionsDetailByUser(userId);
        List<Subject> subjectList=subjectService.getSubjectList();
        List<Knowledge> knowledgeList=knowledgeService.getKnowledgeList();
        int index=0;
        for (Subject s:subjectList) {
            SubjectDemo sd=new SubjectDemo(s.getSubject_id(),s.getSubject_name());
            res.add(sd);
        }
        for (Knowledge k: knowledgeList) {
            KnowledgeDemo kd=new KnowledgeDemo(k.getKnowledge_id(),k.getKnowledge_name(),0);
            for(;index<collects.size();index++){
                QuestionDemo qd= collects.get(index);
                if(qd.getKnowledge_id().equals(k.getKnowledge_id())){
                    Question q=new Question();
                    q.setKnowledge_id(qd.getKnowledge_id());
                    q.setQuestion_answer(qd.getQuestion_answer());
                    q.setQuestion_answer_detail(qd.getQuestion_answer_detail());
                    q.setQuestion_id(qd.getQuestion_id());
                    q.setQuestion_from(qd.getQuestion_from());
                    q.setQuestion_description(qd.getQuestion_description());
                    q.setQuestion_level(qd.getQuestion_level());
                    q.setQuestion_score(qd.getQuestion_score());
                    q.setQuestion_type(qd.getQuestion_type());
                    kd.addQuestionToList(q);
                }
                else break;
            }
            if(kd.getQuestion_num()>0) res.get(k.getSubject_id()-1).addKnowledgeToList(kd);
        }
        res.removeIf(s -> s.getQuestion_num() == 0);
        return res;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<QuestionDemo> searchWrong(String userId,String str){
        str="%"+str+"%";
        return collectService.searchCollect(userId, str);
    }
}
