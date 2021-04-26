package com.example.finalproject;

public class Product {
    private String pName;
    private double pPrice;
    private String pDesc;
    private boolean pAvail;

    public Product(String pName, double pPrice, String pDesc, boolean pAvail) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDesc = pDesc;
        this.pAvail = pAvail;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(Float pPrice) {
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
}
