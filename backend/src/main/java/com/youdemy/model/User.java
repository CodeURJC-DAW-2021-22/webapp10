package com.youdemy.model;

import java.util.List;

import javax.persistence.*;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long id;
	
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	public User() {}

	public User(String firstName, String lastName, String email, String encodedPassword, String... roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
	}

	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setId(long id) {
		this.id = id;
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
	
}
