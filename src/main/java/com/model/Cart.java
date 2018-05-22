package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    private int id;
    private int count;
    @ManyToOne
    @JsonManagedReference
    private User user;
    @ManyToOne
    @JsonManagedReference
    private Food food;

    public Cart(int id,User user,Food food,int count){
        this.id=id;
        this.user=user;
        this.food=food;
        this.count=count;
    }
    public Cart(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
