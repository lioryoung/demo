package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Indent {
    @Id
    private int id;
    private Date date;
    private int sumPrice;
    private boolean deliverType;//false 表示自己配送true表示学生配送
    private int state;//1表示为接单，2表示配送，3表示已完成

    @ManyToOne
    @JsonManagedReference
    private User user;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="indent", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Integer,IndentDetail> indentDetailMap=new HashMap<>();
    public Map<Integer, IndentDetail> getIndentDetailMap() {
        return indentDetailMap;
    }
    public Indent(){}
    public Indent(int id,User user,Date date,int sumPrice,boolean deliverType,int state){
        this.id=id;
        this.date=date;
        this.sumPrice=sumPrice;
        this.deliverType=deliverType;
        this.state=state;
        this.user=user;
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

    public boolean isDeliverType() {
        return deliverType;
    }

    public void setDeliverType(boolean deliverType) {
        this.deliverType = deliverType;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
