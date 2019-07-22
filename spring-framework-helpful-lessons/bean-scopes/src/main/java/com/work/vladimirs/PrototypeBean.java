package com.work.vladimirs;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Scope(value = "prototype")
public class PrototypeBean implements StatefulBean {

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
