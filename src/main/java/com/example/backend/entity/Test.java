package com.example.backend.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Test {
    private int test_id;
    private int test_user_id;
    private String test_file;
    private LocalDateTime test_time;
    private int test_state;

    public int getTest_state() {
        return test_state;
    }

    public void setTest_state(int test_state) {
        this.test_state = test_state;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getTest_user_id() {
        return test_user_id;
    }

    public void setTest_user_id(int test_user_id) {
        this.test_user_id = test_user_id;
    }

    public String getTest_file() {
        return test_file;
    }

    public void setTest_file(String test_file) {
        this.test_file = test_file;
    }

    public LocalDateTime getTest_time() {
        return test_time;
    }

    public void setTest_time(LocalDateTime test_time) {
        this.test_time = test_time;
    }
}
