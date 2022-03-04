package com.youdemy.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String description;
	private String author;
	private String imageURL;
	private int price;
	private String category;

	@OneToMany
	private List<Lesson> lessons;

	public Course(String title, String description, String author, String imageURL, int price, String category, List<Lesson> lessons) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.imageURL = imageURL;
		this.price = price;
		this.category = category;
		this.lessons = lessons;
	}


	public Course() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int precio) {
		this.price = precio;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public void addLesson(Lesson lesson) {
		this.lessons.add(lesson);
	}
}
