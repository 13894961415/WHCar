package com.example.whcar.Home.model;

public class HomeVerGridModel {
    private int img;
    private String title;
    private String xinxi;
    private String time;
    private String num;
    private String price;
    private String primeprice;

    public HomeVerGridModel(int img, String title, String xinxi, String time, String num, String price, String primeprice) {
        this.img = img;
        this.title = title;
        this.xinxi = xinxi;
        this.time = time;
        this.num = num;
        this.price = price;
        this.primeprice = primeprice;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXinxi() {
        return xinxi;
    }

    public void setXinxi(String xinxi) {
        this.xinxi = xinxi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrimeprice() {
        return primeprice;
    }

    public void setPrimeprice(String primeprice) {
        this.primeprice = primeprice;
    }
}


