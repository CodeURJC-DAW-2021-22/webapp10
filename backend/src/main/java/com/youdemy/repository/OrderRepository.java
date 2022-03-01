package com.youdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.Order;

public interface OrderRepository extends  JpaRepository<Order, Long>{
	

}