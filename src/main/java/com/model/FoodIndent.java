package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;


//这个model是代表学生配送的
@Entity
public class FoodIndent {
    @Id
    private int id;
    private Date date;
    private boolean deliverType;//保留默认false
    private boolean state;//false表示已接当 true表示已完成
    @ManyToOne
    @JsonManagedReference
    private User user;
    @OneToOne
    @JsonManagedReference
    private Indent indent;
    public FoodIndent(){
    }
    public FoodIndent(int id,Date date,boolean deliverType,boolean state,User user,Indent indent){
        this.id=id;
        this.date=date;
        this.deliverType=false;
        this.state=false;
        this.user=user;
        this.indent=indent;
    }
    public Indent getIndent() {
        return indent;
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

    public boolean isDeliverType() {
        return deliverType;
    }

    public void setDeliverType(boolean deliverType) {
        this.deliverType = deliverType;
    }

    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
