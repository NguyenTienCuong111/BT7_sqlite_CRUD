package com.example.bt_sqlite_crud.model;

public class Employee {
    private String id, name;
    private float classs;

    public Employee() {
    }

    public Employee(String id, String name, float classs) {
        this.id = id;
        this.name = name;
        this.classs = classs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getClasss() {
        return classs;
    }

    public void setClasss(float classs) {
        this.classs = classs;
    }
}
