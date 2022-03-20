package com.youdemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youdemy.model.OrderP;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderPRepository extends  JpaRepository<OrderP, Long>{

    List<OrderP> findByUser(Long userId);

    long countByCourse(long id);

}