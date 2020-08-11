package com.infosys.api.order.aggregate;

import java.util.List;

public class Cart {
    private String cartIdentifier;

    private String name;

    private List<String> items;


    public Cart(String cartIdentifier, String name, List<String> items) {
        this.cartIdentifier = cartIdentifier;
        this.name = name;
        this.items = items;
    }

    public String getCartIdentifier() {
        return cartIdentifier;
    }

    public void setCartIdentifier(String cartIdentifier) {
        this.cartIdentifier = cartIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
