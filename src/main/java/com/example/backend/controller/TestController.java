package com.example.backend.controller;

import com.example.backend.entity.Question;
import com.example.backend.entity.Subject;
import com.example.backend.entity.Test;
import com.example.backend.entity.TestQuestionDemo;
import com.example.backend.service.KnowledgeService;
import com.example.backend.service.QuestionService;
import com.example.backend.service.SubjectService;
import com.example.backend.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    QuestionService questionService;

    @Autowired
    KnowledgeService knowledgeService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping(value = "/getTests",method = RequestMethod.GET)
    public Object[] getTests(String userId) throws IOException {
        Object[] res=new Object[3];
        res[0]=false;
        res[1]=null;
        res[2]=null;
        List<Test> tests=testService.getTestListByUser(userId);
        if(tests.size()>0) {
            if (tests.get(0).getTest_state() == 0) {
                res[0] = true;
                res[1] = tests.get(0);
                String path = tests.get(0).getTest_file();
                FileReader fileReader = new FileReader(new File(path));
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[1024];
                int size;
                while ((size = fileReader.read(buffer)) != -1) {
                    stringBuilder.append(buffer, 0, size);
                }
                res[2] = stringBuilder.toString();
            }
        }
        return res;
    }

    @RequestMapping("/getTest")
    public String getTest(int testId) throws IOException{
        Test test= testService.getTestById(testId);
        String path = test.getTest_file();
        FileReader fileReader = new FileReader(path);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int size;
        while ((size = fileReader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, size);
        }
        return stringBuilder.toString();
    }

    @RequestMapping("/getPreTests")
    public Object[] getPreTests(String userId) throws IOException{
        Object[] res=new Object[2];
        List<Test> tests=testService.getTestListByUser(userId);
        res[0]=tests;
        ArrayList<Integer> scores=new ArrayList<>();
        res[1]=null;
        for(Test t:tests){
            String path = t.getTest_file();
            FileReader fileReader = new FileReader(path);
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[1024];
            int size;
            while ((size = fileReader.read(buffer)) != -1) {
                stringBuilder.append(buffer, 0, size);
            }
            String testJson=stringBuilder.toString();
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String,Object> testMap=objectMapper.readValue(testJson,Map.class);
            scores.add((int)testMap.get("test_score"));
        }
        res[1]=scores;
        return res;
    }

    @RequestMapping("/newTest")
    public Object[] newTest(String userId) throws IOException {
        Object[] res=new Object[2];
        res[0]=null;
        res[1]=null;
        Test test=new Test();
        test.setTest_user_id(Integer.parseInt(userId));
        test.setTest_time(LocalDateTime.now());
        test.setTest_state(0);
        test.setTest_file("");
        testService.insertTest(test);
        int testId=testService.getLastTestId();
        test.setTest_id(testId);
        ObjectMapper objectMapper=new ObjectMapper();
        ObjectNode testNode=objectMapper.createObjectNode();
        testNode.put("test_id",testId);
        testNode.put("test_user_id",userId);
        testNode.put("test_time",test.getTest_time().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        testNode.put("test_state",test.getTest_state()==0?"进行中":"已完成");
        testNode.put("test_score",0);
        List<Question> questions=new ArrayList<>();
        List<Subject> subjects=subjectService.getSubjectList();
        for (Subject s:subjects) {
            List<String> knowledgeIds=knowledgeService.getKnowledgeIdsBySubject(s.getSubject_id());
            List<Question> chooses=questionService.getQuestionByKnowledgeIdsAndType(1,knowledgeIds);
            int length=chooses.size();
            if(s.getSubject_id()==1){
                int num=10;
                HashSet<Integer> r=new HashSet<>();
                while(r.size()<num) r.add((int)(Math.random()*(length-1)));
                for (Integer i:r) {
                    questions.add(chooses.get(i));
                }
            }
            else if(s.getSubject_id()==2){
                int num=12;
                HashSet<Integer> r=new HashSet<>();
                while(r.size()<num) r.add((int)(Math.random()*(length-1)));
                for (Integer i:r) {
                    questions.add(chooses.get(i));
                }
            }
            else if(s.getSubject_id()==3){
                int num=10;
                HashSet<Integer> r=new HashSet<>();
                while(r.size()<num) r.add((int)(Math.random()*(length-1)));
                for (Integer i:r) {
                    questions.add(chooses.get(i));
                }
            }
            else {
                int num=8;
                HashSet<Integer> r=new HashSet<>();
                while(r.size()<num) r.add((int)(Math.random()*(length-1)));
                for (Integer i:r) {
                    questions.add(chooses.get(i));
                }
            }
        }
        for (Subject s:subjects){
            List<String> knowledgeIds=knowledgeService.getKnowledgeIdsBySubject(s.getSubject_id());
            if(s.getSubject_id()==1){
                List<Question> q_10=questionService.getQuestionByKnowledgeIdsAndScore(10,knowledgeIds);
                List<Question> q_15=questionService.getQuestionByKnowledgeIdsAndScore(15,knowledgeIds);
                int n_10=(int)(Math.random()*(q_10.size()-1));
                int n_15=(int)(Math.random()*(q_15.size()-1));
                questions.add(q_10.get(n_10));
                questions.add(q_15.get(n_15));
            }
            else if(s.getSubject_id()==2){
                List<Question> q_8=questionService.getQuestionByKnowledgeIdsAndScore(8,knowledgeIds);
                List<Question> q_13=questionService.getQuestionByKnowledgeIdsAndScore(13,knowledgeIds);
                int n_8=(int)(Math.random()*(q_8.size()-1));
                int n_13=(int)(Math.random()*(q_13.size()-1));
                questions.add(q_8.get(n_8));
                questions.add(q_13.get(n_13));
            }
            else if(s.getSubject_id()==3){
                List<Question> q_7=questionService.getQuestionByKnowledgeIdsAndScore(7,knowledgeIds);
                List<Question> q_8=questionService.getQuestionByKnowledgeIdsAndScore(8,knowledgeIds);
                int n_7=(int)(Math.random()*(q_7.size()-1));
                int n_8=(int)(Math.random()*(q_8.size()-1));
                questions.add(q_7.get(n_7));
                questions.add(q_8.get(n_8));
            }
            else {
                List<Question> q_9=questionService.getQuestionByKnowledgeIdsAndScore(9,knowledgeIds);
                int n_9=(int)(Math.random()*(q_9.size()-1));
                questions.add(q_9.get(n_9));
            }
        }
        List<TestQuestionDemo> testQuestionDemos=new ArrayList<>();
        for (Question q:questions) {
            TestQuestionDemo t=new TestQuestionDemo();
            t.setIndex(questions.indexOf(q)+1);
            t.setQuestion_id(q.getQuestion_id());
            t.setKnowledge_id(q.getKnowledge_id());
            t.setQuestion_type(q.getQuestion_type());
            t.setQuestion_from(q.getQuestion_from());
            t.setQuestion_score(q.getQuestion_score());
            t.setQuestion_level(q.getQuestion_level());
            t.setQuestion_description(q.getQuestion_description());
            t.setQuestion_answer(q.getQuestion_answer());
            t.setQuestion_answer_detail(q.getQuestion_answer_detail());
            t.setQuestion_state("default");
            t.setMy_answer("");
            t.setMy_score(0);
            testQuestionDemos.add(t);
        }
        String questionsStr= objectMapper.writeValueAsString(testQuestionDemos);
        testNode.put("questions",questionsStr);
        String testStr= objectMapper.writeValueAsString(testNode);
        String filePath="/usr/local/software/projects/question_bank/tests/";
        File pathFile=new File(filePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        File file=new File(filePath+test.getTest_id());
        FileWriter fileWriter=new FileWriter(file,false);
        fileWriter.write(testStr);
        fileWriter.flush();
        fileWriter.close();
        test.setTest_file(file.getPath());
        testService.updateTest(test);
        res[0]=test;
        res[1]=testStr;
        return res;
    }

    @RequestMapping(value = "/answerSelect",method = RequestMethod.GET)
    public String answerSelect(int testId,int index,String answer) throws IOException {
        Test test= testService.getTestById(testId);
        String path = test.getTest_file();
        FileReader fileReader = new FileReader(path);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int size;
        while ((size = fileReader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, size);
        }
        String testJson=stringBuilder.toString();
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> testMap=objectMapper.readValue(testJson,Map.class);
        String questionsStr= (String) testMap.get("questions");
        List<Map<String,Object>> questions=objectMapper.readValue(questionsStr, new TypeReference<List<Map<String, Object>>>() {});
        Map<String,Object> q=questions.get(index-1);
        q.put("question_state","primary");
        q.put("my_answer",answer);
        questions.set(index-1,q);
        questionsStr= objectMapper.writeValueAsString(questions);
        testMap.put("questions",questionsStr);
        testJson= objectMapper.writeValueAsString(testMap);
        FileWriter fileWriter=new FileWriter(path,false);
        fileWriter.write(testJson);
        fileWriter.flush();
        fileWriter.close();
        return testJson;
    }

    @RequestMapping("/answerWriting")
    public String answerWriting(int testId,int index,String answer) throws IOException{
        return answerSelect(testId, index, answer);
    }

    @RequestMapping("/handUp")
    public String handUp(int testId) throws IOException{
        Test test= testService.getTestById(testId);
        String path = test.getTest_file();
        FileReader fileReader = new FileReader(path);
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int size;
        while ((size = fileReader.read(buffer)) != -1) {
            stringBuilder.append(buffer, 0, size);
        }
        String testJson=stringBuilder.toString();
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> testMap=objectMapper.readValue(testJson,Map.class);
        String questionsStr= (String) testMap.get("questions");
        List<Map<String,Object>> questions=objectMapper.readValue(questionsStr, new TypeReference<List<Map<String, Object>>>() {});
        int totalScore=0;
        for(Map<String,Object> m:questions){
            if((int)m.get("question_type")==1){
                boolean t=m.get("my_answer").equals(m.get("question_answer"));
                m.put("question_state",t?"success":"danger");
                m.put("my_score",t? m.get("question_score") :0);
                if(t) totalScore+=(int)m.get("question_score");
                questions.set((int)m.get("index")-1,m);
            }
            else{
                m.put("question_state","warning");
            }
        }
        questionsStr= objectMapper.writeValueAsString(questions);
        testMap.put("questions",questionsStr);
        testMap.put("test_state","已完成");
        testMap.put("test_score",totalScore);
        testJson= objectMapper.writeValueAsString(testMap);
        FileWriter fileWriter=new FileWriter(path,false);
        fileWriter.write(testJson);
        fileWriter.flush();
        fileWriter.close();
        test.setTest_state(1);
        testService.updateTest(test);
        return testJson;
    }
}
