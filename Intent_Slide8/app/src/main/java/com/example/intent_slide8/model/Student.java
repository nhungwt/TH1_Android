package com.example.intent_slide8.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int img, age;
    private String name;

    public Student(int img, String name, int age) {
        this.img = img;
        this.age = age;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
