package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdemy.model.OrderP;
import com.youdemy.repository.OrderPRepository;

@Service
public class OrderPService {
	
	@Autowired
	private OrderPRepository repository;
	
	public Optional<OrderP> findById(long id) {
		return repository.findById(id);
	}

	public List<OrderP> findByUserId(long userId) {
		return repository.findByUser(userId);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<OrderP> findAll() {
		return repository.findAll();
	}

	public void save(OrderP order) {
		repository.save(order);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

	public long countByCourse(long id) {
		return repository.countByCourse(id);
	}

}