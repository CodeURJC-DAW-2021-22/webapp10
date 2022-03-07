package com.youdemy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String description;
	private int price;

	@ElementCollection
	private List<String> tags;

	@Lob
	@JsonIgnore
	private byte[] thumbnail;

	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Lesson> lessons;

	@ManyToOne
	private User author;

	public Course(String title, String description, int price, byte[] thumbnail, List<String> tags, List<Lesson> lessons, User author) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.thumbnail = thumbnail;
		this.tags = tags;
		this.lessons = lessons;
		this.author = author;
	}

	public Course() {}

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

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
