package com.youdemy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youdemy.model.OrderP;
import com.youdemy.service.OrderPService;

@RestController
@RequestMapping("/api/orders")
public class OrderPRestController {
	
	@Autowired
	private OrderPService orderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderP> getOrder(@PathVariable long id) {
		
		Optional<OrderP> order = orderService.findById(id);			
		if (order.isPresent()) {
			OrderP dbOrder = order.get();
			return new ResponseEntity<>(dbOrder, HttpStatus.OK);		
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // trying access to not present resource
		}		
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public OrderP createBook(@RequestBody OrderP order) {

		orderService.save(order);

		return order;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Optional<OrderP>> replaceOrder(@PathVariable long id, @RequestBody OrderP newOrder) {
	    Optional<OrderP> put = orderService.findById(id);
	    
	      if (put != null) {
	          newOrder.setId(id);
	          orderService.save(newOrder);
	          return ResponseEntity.ok(put);
	      } else {
	          return ResponseEntity.notFound().build();
	      }
	 }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<OrderP> deleteOrder(@PathVariable long id) {

		try {
			orderService.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}	
}