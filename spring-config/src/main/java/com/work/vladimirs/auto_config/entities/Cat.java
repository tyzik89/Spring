package com.work.vladimirs.auto_config.entities;

import org.springframework.stereotype.Component;

@Component
public class Cat {
    private String name = "Вася";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
