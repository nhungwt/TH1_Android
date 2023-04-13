package com.example.th_03_03.model;

public class Tour {
    private int img;
    private String name, time;

    public Tour(int img, String name, String time) {
        this.img = img;
        this.name = name;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
