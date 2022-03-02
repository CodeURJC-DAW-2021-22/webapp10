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

}
