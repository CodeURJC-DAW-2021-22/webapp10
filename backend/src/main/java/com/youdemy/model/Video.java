package com.youdemy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String course;


	public Video(String title, String description, String author, String imageURL, int duration, String course ) {
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
