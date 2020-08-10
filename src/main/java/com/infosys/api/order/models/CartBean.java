package com.infosys.api.order.models;


public class CartBean {
	private  String cartIdentifier;
	private  String name;

	public CartBean(){

	}
	public CartBean(String cartIdentifier, String name) {
		this.cartIdentifier = cartIdentifier;
		this.name = name;
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
}
