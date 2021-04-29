package com.example.finalproject;

public class Product {
    private String pName;
    private String pPrice;
    private String pDesc;
    private boolean pAvail;
    private String imageLink;

    public Product(String pName, String pPrice, String pDesc, boolean pAvail, String imageLink) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDesc = pDesc;
        this.pAvail = pAvail;
        this.imageLink = imageLink;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public boolean ispAvail() {
        return pAvail;
    }

    public void setpAvail(boolean pAvail) {
        this.pAvail = pAvail;
    }

    public String getImageLink(){
        return imageLink;
    }
}
