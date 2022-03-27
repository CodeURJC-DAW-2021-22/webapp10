package com.youdemy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String videoUrl;
	private long imageId;

	@ManyToOne
	private User author;

	@ManyToOne
	@JsonIgnore
	private Course course;

	public Lesson(String title, String description, User author, Course course, String videoUrl, long imageId) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.course = course;
		this.videoUrl = videoUrl;
		this.imageId = imageId;
	}
	
	public Lesson(String title, String description, Course course, String videoUrl, long imageId) {
		super();
		this.title = title;
		this.description = description;
		this.course = course;
		this.videoUrl = videoUrl;
		this.imageId = imageId;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}
