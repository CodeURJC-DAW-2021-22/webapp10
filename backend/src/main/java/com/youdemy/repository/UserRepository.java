package com.youdemy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByFirstName(String firstName);

}
