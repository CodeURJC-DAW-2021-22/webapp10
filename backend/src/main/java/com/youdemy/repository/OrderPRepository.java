package com.youdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.OrderP;

public interface OrderPRepository extends  JpaRepository<OrderP, Long>{
	

}