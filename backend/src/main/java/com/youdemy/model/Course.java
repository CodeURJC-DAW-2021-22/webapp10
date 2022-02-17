package com.youdemy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String description;
	private String author;
	private String imageURL;
	private int precio;
	private String category;


	public Course(String title, String description, String author, String imageURL, int precio, String category) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.imageURL = imageURL;
		this.precio = precio;
		this.category = category;
	}


	public Course() {

	}
}
