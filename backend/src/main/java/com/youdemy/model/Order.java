package com.youdemy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Order {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
 
	private int price;
	
	@ManyToOne
	private User user; 
	@OneToOne
 	private Course course;
	
	public Order() {}
	
	public Order( User user, int price, Course course) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}	
}