package com.youdemy.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class OrderP {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private int price;
	private long user; 
 	private long course;
 	private String paymentMethod;
 	private String billingAddress;
 	private String country;
 	private String region;
 	private String dataCard;
 	private String date;
	
	public OrderP() {}
	
	public OrderP( long user, int price, long course, String paymentMethod,
					String billingAddress, String country, String region,
					String dataCard) {
		super();
		this.user = user;
		this.price = price;
		this.course = course;
		this.paymentMethod = paymentMethod;
		this.billingAddress = billingAddress;
		this.country = country;
		this.region = region;
		this.dataCard = dataCard;
		this.date = this.Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public long getCourse() {
		return course;
	}

	public void setCourse(long course) {
		this.course = course;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getbillingAddress() {
		return billingAddress;
	}

	public void setbillingAddres(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getDataCard() {
		return dataCard;
	}

	public void setDataCard(String dataCard) {
		this.dataCard = dataCard;
	}
	
	public String Date() {
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = dateFormatter.format(new Date(System.currentTimeMillis()));
		return date;
	}
}