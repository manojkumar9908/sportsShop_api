package com.sportsshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sportsshop.model.Order;
import com.sportsshop.service.OrderService;

@RestController
public class OrderDetailController {

	@Autowired
	OrderService orderService;

	@PostMapping("orders/save")
	public ResponseEntity<String> save(@RequestBody Order order) {
		try {

			orderService.save(order);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("orders/orderlist")
	public List<Order> findAll() {
		List<Order> listOrder = null;
		try {

			listOrder = orderService.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	@GetMapping("orders/{search}") // find by user IdF
	public Order findById(@PathVariable("id") Integer id) {
		System.out.println("findById " + id);
		Order order = orderService.findById(id);
		return order;
	}

	@GetMapping("orders/{id}/change-status")
	public void changeStatus(@PathVariable("id") Integer id, @RequestParam("status") String status) {
		orderService.changeStatus(id, status);

	}

}
