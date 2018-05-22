package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class PinUser {
    @Id
    private int id;
    @ManyToOne
    @JsonManagedReference
    private User user;

    public PinUser(){
    }
    public PinUser(int id,User user){
        this.id=id;
        this.user=user;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUse() {
        return user;
    }

    public void setUse(User use) {
        this.user = use;
    }

}
