package com.youdemy.service;

import com.youdemy.model.OrderP;
import com.youdemy.model.User;
import com.youdemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
       return userRepository.findAll();
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
	
	public boolean existByEmail(String mail) {
		if(userRepository.findByFirstName(mail) == null) {
			return true;
		}
		return false;
	}
	

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(long id) {
		userRepository.deleteById(id);
	}
	
	public Page<User> getUsers(int page) {
		return userRepository.findAll(PageRequest.of(page, 3));
	}
}
