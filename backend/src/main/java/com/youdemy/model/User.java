package com.youdemy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Yuser")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long id;
	
	@Column(unique = true)
	private String email;
	
	private String firstName;
	private String lastName;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {}

	public User(String email, String firstName, String lastName, String encodedPassword, String... roles) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return email;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
