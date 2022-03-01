package com.youdemy.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Video {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	private String title; 
	private String description;
	private String author;
	private boolean image;
	
	@Lob
	private Blob imageField;
	private int duration;
	
	@ManyToOne
	private Course course;
	
	public Video() {}
	
	public Video(String title, String description) {
		super();
		this.title = title;
		this.description = description;
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


	public Blob getImageFile() {
		return imageField;
	}

    public void setImageFile(Blob image) {
		this.imageField = image;
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
	
	public boolean getImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}
	
}