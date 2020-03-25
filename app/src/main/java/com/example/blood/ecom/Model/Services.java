package com.example.blood.ecom.Model;

public class Services

{


    private String Description ,pname , Image , price ,pid , Time , Catagory , Date, pcity , pstatus;

    public Services() {
    }
    public Services(String description, String pname, String image, String price, String pid, String time, String catagory, String date, String pcity, String pstatus) {
        Description = description;
        this.pname = pname;
        Image = image;
        this.price = price;
        this.pid = pid;
        Time = time;
        Catagory = catagory;
        Date = date;
        this.pcity = pcity;
        this.pstatus = pstatus;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPcity() {
        return pcity;
    }

    public void setPcity(String pcity) {
        this.pcity = pcity;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }
}
