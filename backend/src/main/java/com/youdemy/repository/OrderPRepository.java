package com.youdemy.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youdemy.model.OrderP;

import antlr.collections.List;

public interface OrderPRepository extends  JpaRepository<OrderP, Long>{
	
	@Query("SELECT o FROM OrderP o WHERE o.user = ?1")
	ArrayList <OrderP> findByUser(long user);	
}