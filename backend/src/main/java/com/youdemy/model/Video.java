package com.youdemy.model;

import javax.persistence.*;

@Entity
public class Video {

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

	public Video(String title, String description, String author, String imageURL, int duration, Course course) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.imageURL = imageURL;
		this.duration = duration;
		this.course = course;
	}


	public Video() {

	}

}
