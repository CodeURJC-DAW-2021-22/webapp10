package com.youdemy.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youdemy.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByFirstName(String firstName);
	
	Optional<User> findByEmail(String email);
	
	Page<User> findAll(Pageable page);

}


