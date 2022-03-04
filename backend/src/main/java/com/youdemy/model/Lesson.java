package com.youdemy.model;

import javax.persistence.*;

@Entity
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String description;
	private String author;
	private String imageURL;
	private int duration;

	@ManyToOne
	private Course course;

	private long thumbnailId;

	public Lesson(String title, String description, String author, String imageURL, int duration, Course course, long thumbnailId) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.imageURL = imageURL;
		this.duration = duration;
		this.course = course;
		this.thumbnailId = thumbnailId;
	}

	public Lesson() {}

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public long getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(long thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
}
