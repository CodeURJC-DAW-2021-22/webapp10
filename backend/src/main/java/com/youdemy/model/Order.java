package com.youdemy.model;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Order {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
 
	private int price;
	
	@ManyToOne
	private Optional<User> user; 
	@OneToOne
 	private Optional<Course> course;
	
	public Order() {}
	
	public Order( User user, int price, Course course) {
		super();
		this.user = Optional.ofNullable(user);
		this.price = price;
		this.course = Optional.ofNullable(course);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Optional<User> getUser() {
		return user;
	}

	public void setUser(Optional<User> user) {
		this.user = user;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Optional<Course> getCourse() {
		return course;
	}

	public void setCourse(Optional<Course> course) {
		this.course = course;
	}	
}