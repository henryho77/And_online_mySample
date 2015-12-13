package com.example.listviewchecktest.model;

public class BookInfo {
	
	private String title;
	private String price;
	private String date;

	public BookInfo(String title, String price, String date) {
		this.title = title;
		this.price = price;
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
}
