package com.example.backend.controller;

import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @Autowired
    CollectService collectService;

    @Autowired
    AnswerService answerService;

    @Autowired
    WrongService wrongService;

    @Autowired
    KnowledgeService knowledgeService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/getComments",method = RequestMethod.GET)
    public List<CommentDemo> getComments(String questionId){
        return commentService.getCommentListByQuestionOrderByTime(questionId);
    }

    @RequestMapping(value = "/getCommentNum",method = RequestMethod.GET)
    public int getCommentNum(String questionId){
        return commentService.getCommentNumByQuestion(questionId);
    }

    @RequestMapping(value = "/isCollected",method = RequestMethod.GET)
    public boolean isCollected(String userId,String questionId){
        System.out.println(userId+" "+questionId);
        Collect c=collectService.getCollectByUserAndQuestion(userId, questionId);
        System.out.println(c==null);
        return c!=null;
    }

    @RequestMapping(value = "/getUserAnswer",method = RequestMethod.GET)
    public Object[] getUserAnswer(String userId,String questionId){
        Object[] res=new Object[3];
        Answer answer=answerService.getAnswerByUserAndQuestion(userId, questionId);
        if(answer==null) {
            res[0]=false;
            res[1]=null;
            res[2]=0;
        }
        else{
            res[0]=true;
            res[1]=answer.getAnswer_content();
            res[2]=answer.getAnswer_score();
        }
        return res;
    }

    @RequestMapping(value = "/answerQuestionSelect",method = RequestMethod.GET)
    public String answerQuestion(String userId,String questionId,String answer){
        Question question=questionService.getQuestionById(questionId);
        String trueAnswer=question.getQuestion_answer();
        int questionScore=question.getQuestion_score();
        Wrong preWrong= wrongService.getWrong(userId, questionId);
        if(preWrong!=null) wrongService.deleteWrong(preWrong);
        Answer answer1=answerService.getAnswerByUserAndQuestion(userId, questionId);
        if(answer1!=null){
            if(!answer.equals(trueAnswer)){
                Wrong wrong=new Wrong();
                wrong.setWrong_user_id(userId);
                wrong.setWrong_question_id(questionId);
                wrongService.insertWrong(wrong);
                answerService.updateAnswer(userId, questionId, answer,0);
            }
            else {
                answerService.updateAnswer(userId, questionId, answer,questionScore);
            }
        }
        else{
            Answer newAnswer=new Answer();
            newAnswer.setAnswer_user_id(userId);
            newAnswer.setAnswer_question_id(questionId);
            newAnswer.setAnswer_content(answer);
            if(!answer.equals(trueAnswer)){
                Wrong wrong=new Wrong();
                wrong.setWrong_user_id(userId);
                wrong.setWrong_question_id(questionId);
                wrongService.insertWrong(wrong);
                newAnswer.setAnswer_score(0);
            }
            else {
                newAnswer.setAnswer_score(questionScore);
            }
            answerService.insertAnswer(newAnswer);
        }
        return answer;
    }

    @RequestMapping(value = "/answerQuestionText",method = RequestMethod.GET)
    public String answerQuestionText(String userId,String questionId,String answer){
        Wrong preWrong= wrongService.getWrong(userId, questionId);
        if(preWrong!=null) wrongService.deleteWrong(preWrong);
        Answer answer1=answerService.getAnswerByUserAndQuestion(userId, questionId);
        if(answer1!=null) answerService.updateAnswer(userId,questionId,answer,0);
        else{
            Answer newAnswer=new Answer();
            newAnswer.setAnswer_user_id(userId);
            newAnswer.setAnswer_question_id(questionId);
            newAnswer.setAnswer_content(answer);
            newAnswer.setAnswer_score(0);
            answerService.insertAnswer(newAnswer);
        }
        Wrong newWrong=new Wrong();
        newWrong.setWrong_user_id(userId);
        newWrong.setWrong_question_id(questionId);
        wrongService.insertWrong(newWrong);
        return answer;
    }

    @RequestMapping(value = "/getQuestionsState",method = RequestMethod.GET)
    public String[] getQuestionsState(String userId,@RequestParam String questionIds){
        String[] ids=questionIds.split(",");
        String[] res=new String[ids.length];
        for (int i=0;i<ids.length;i++) {
            if(wrongService.getWrong(userId,ids[i])!=null) res[i]="danger";
            else if(answerService.getAnswerByUserAndQuestion(userId,ids[i])!=null) res[i]="success";
            else res[i]="default";
        }
        return res;
    }

    @RequestMapping(value = "/submitScore",method = RequestMethod.GET)
    public int submitScore(String userId,String questionId,int score){
        Answer answer=answerService.getAnswerByUserAndQuestion(userId, questionId);
        if(answer==null) return -1;
        Wrong wrong= wrongService.getWrong(userId,questionId);
        if(wrong!=null) wrongService.deleteWrong(wrong);
        answerService.updateAnswer(userId,questionId,answer.getAnswer_content(),score);
        Question question= questionService.getQuestionById(questionId);
        if(score<question.getQuestion_score()){
            Wrong newWrong=new Wrong();
            newWrong.setWrong_user_id(userId);
            newWrong.setWrong_question_id(questionId);
            wrongService.insertWrong(newWrong);
        }
        return score;
    }

    @RequestMapping(value = "/noCollect",method = RequestMethod.GET)
    public boolean noCollect(String userId,String questionId){
        Collect collect=collectService.getCollectByUserAndQuestion(userId, questionId);
        if(collect==null) return false;
        collectService.deleteCollect(collect);
        return true;
    }

    @RequestMapping(value = "/collect",method = RequestMethod.GET)
    public boolean collect(String userId,String questionId){
        Collect collect=collectService.getCollectByUserAndQuestion(userId, questionId);
        if(collect!=null) return false;
        Collect newCollect=new Collect();
        newCollect.setCollect_user_id(userId);
        newCollect.setCollect_question_id(questionId);
        collectService.insertCollect(newCollect);
        return true;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<QuestionDemo> searchQuestion(@RequestParam String str){
        str="%"+str+"%";
        List<Question> questions=questionService.searchQuestion(str);
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
}
