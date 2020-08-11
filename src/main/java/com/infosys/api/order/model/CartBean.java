package com.infosys.api.order.model;

public class CartBean {

    String name;

    public CartBean(){

    }

    public CartBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
