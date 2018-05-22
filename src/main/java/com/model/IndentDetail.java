package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class IndentDetail implements Serializable{

    @Id
    private int id;
    private int num;
    private int sumPrice;
    private String comment;
    private boolean commentState;

    @ManyToOne
    @JsonManagedReference
    private Food food;
    @ManyToOne
    @JsonManagedReference
    private Indent indent;

    public IndentDetail(){}
    public  IndentDetail(int id,int num,int sumPrice,String comment,boolean commentState,Food food,Indent indent){
        this.id=id;
        this.num=num;
        this.sumPrice=sumPrice;
        this.food=food;
        this.comment=comment;
        this.commentState=commentState;
        this.indent=indent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCommentState() {
        return commentState;
    }

    public void setCommentState(boolean commentState) {
        this.commentState = commentState;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Indent getIndent() {
        return indent;
    }

    public void setIndent(Indent indent) {
        this.indent = indent;
    }
}
