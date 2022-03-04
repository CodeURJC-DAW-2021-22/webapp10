package com.youdemy.service;

import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void findAll() {
        userRepository.findAll();
    }

    public User findByFirstName(String firstName) {
        Optional<User> optionalUser = userRepository.findByFirstName(firstName);

        if (optionalUser.isPresent()) return optionalUser.get();

        throw new RuntimeException("User not found");
    }

	public Optional<User> findById(long id) {
		return userRepository.findById(id);
	}

	public boolean exist(long id) {
		return userRepository.existsById(id);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}
}
