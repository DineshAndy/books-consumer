package com.qaautomated.controller;

import org.springframework.stereotype.Component;

@Component
public class AllToysDetails {

	private  int price;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getToys_name() {
		return toys_name;
	}
	public void setToys_name(String toys_name) {
		this.toys_name = toys_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String toys_name;
	private String category;
	private  String id;
	
	
	
}
