package com.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String alipay;
    private String password;
    private int account;//账户余额
    private String phone;

    public User(){}
    public User(String name,String address,String alipay,String password,String phone){
        this.name=name;
        this.address=address;
        this.alipay=alipay;
        this.password=password;
        this.phone=phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Integer,Indent> indentMap=new HashMap<>();
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Integer,Cart> cartMap=new HashMap<>();
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
    @MapKeyColumn(name="id")
    private Map<Integer,FoodIndent> foodIndentMap=new HashMap<>();

    public Map<Integer, Cart> getCartMap() {
        return cartMap;
    }

    public void setIndentMap(Map<Integer, Indent> indentMap) {
        this.indentMap = indentMap;
    }

    public Map<Integer, FoodIndent> getFoodIndentMap() {
        return foodIndentMap;
    }

    public void setFoodIndentMap(Map<Integer, FoodIndent> foodIndentMap) {
        this.foodIndentMap = foodIndentMap;
    }

    public Map<Integer, Indent> getIndentMap() {
        return indentMap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
