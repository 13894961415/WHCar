package com.example.whcar.Buy.Model;

public class BuyListModel {
    private int img;
    private String name;
    private String xinxi;
    private String time;
    private String km;
    private String area;
    private String price;
    private String newprice;

    public BuyListModel(int img, String name, String xinxi, String time, String km, String area, String price, String newprice) {
        this.img = img;
        this.name = name;
        this.xinxi = xinxi;
        this.time = time;
        this.km = km;
        this.area = area;
        this.price = price;
        this.newprice = newprice;
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

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNewprice() {
        return newprice;
    }

    public void setNewprice(String newprice) {
        this.newprice = newprice;
    }
}
