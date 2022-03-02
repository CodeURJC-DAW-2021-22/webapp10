package com.youdemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youdemy.model.Order;
import com.youdemy.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Optional<Order> findById(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	public void save(Order order) {
		repository.save(order);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
