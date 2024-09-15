package com.qaautomated.controller;

import org.springframework.stereotype.Component;

@Component
public class ProductsPrices {

	
	public long getBooksPrice() {
		return booksPrice;
	}
	public void setBooksPrice(long booksPrice) {
		this.booksPrice = booksPrice;
	}
	public long getToysPrice() {
		return toysPrice;
	}
	public void setToysPrice(long toysPrice) {
		this.toysPrice = toysPrice;
	}
	long booksPrice;
	long  toysPrice;
	
}
