package com.youdemy;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.springframework.web.multipart.MultipartFile;

import antlr.collections.List;

@Entity
public class Video {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String title;
	private String author;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	@Lob
	private Blob imageField;

	private int duration;
	
	private String course;
	
	public Video () {}
	
	public Video(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public Long getId() {
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
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = author;
	}
	
	//@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", description=" + description + "]";
	}

}






