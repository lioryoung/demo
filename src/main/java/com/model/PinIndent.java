package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class PinIndent {
    @Id
    private int id;
    private Date date;
    private int sumPrice;
    private int peopleNum;
    private int nowNum;
    private boolean success;
    private String address;
    @ManyToOne
    @JsonManagedReference
    private User user;
    public PinIndent(){}
    public PinIndent(int id,Date date,int sumPrice,int peopleNum,int nowNum,boolean success,String address,User user){
        this.id=id;
        this.date=date;
        this.success=success;
        this.sumPrice=sumPrice;
        this.peopleNum=peopleNum;
        this.address=address;
        this.nowNum=nowNum;
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }



    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }






}
