package com.work.vladimirs.rocketscloud.models;

public class Rocket {

    private String name;

    public Rocket() {
    }

    public Rocket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
