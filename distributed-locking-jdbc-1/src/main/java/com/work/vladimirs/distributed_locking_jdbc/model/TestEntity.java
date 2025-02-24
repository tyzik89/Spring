package com.work.vladimirs.distributed_locking_jdbc.model;

public class TestEntity {

    private Long id;
    private String field;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
