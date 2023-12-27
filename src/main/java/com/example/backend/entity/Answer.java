package com.example.backend.entity;

public class Answer {
    private String answer_user_id;
    private String answer_question_id;
    private String answer_content;
    private int answer_score;

    public int getAnswer_score() {
        return answer_score;
    }

    public void setAnswer_score(int answer_score) {
        this.answer_score = answer_score;
    }

    public String getAnswer_user_id() {
        return answer_user_id;
    }

    public void setAnswer_user_id(String answer_user_id) {
        this.answer_user_id = answer_user_id;
    }

    public String getAnswer_question_id() {
        return answer_question_id;
    }

    public void setAnswer_question_id(String answer_question_id) {
        this.answer_question_id = answer_question_id;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
