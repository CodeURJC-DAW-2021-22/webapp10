package com.youdemy.model;

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
	
	public OrderP() {}
	
	public OrderP( long user, int price, long course) {
		super();
		this.user = user;
		this.price = price;
		this.course = course;
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
}