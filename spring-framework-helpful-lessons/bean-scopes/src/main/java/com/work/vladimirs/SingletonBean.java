package com.work.vladimirs;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SingletonBean implements StatefulBean {

    private String state;

    @PostConstruct
    public void init() {
        this.state = "Initial state";
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
