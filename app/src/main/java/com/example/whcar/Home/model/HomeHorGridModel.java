package com.example.whcar.Home.model;

public class HomeHorGridModel {
    private int img;
    private String txt;

    public HomeHorGridModel(int img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
