package com.sportsshop.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportsshop.dao.UserRepository;
import com.sportsshop.dto.MessageDTO;
import com.sportsshop.model.User;
import com.sportsshop.service.UserService;

@RestController

public class UserDetailsController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@PostMapping("user/save") // register
	public ResponseEntity<?> save(@RequestBody User user) {
		try {
			userService.save(user);
			MessageDTO message = new MessageDTO("Success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			MessageDTO message = new MessageDTO(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("user/userlist")
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@DeleteMapping("user/{id}")
	public void delete(@PathVariable("id") Integer id) {
		log.debug(" deletebyid -{}", id);
		userRepository.deleteById(id);
	}

	@PutMapping("user/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody User user) {
		user.setId(id);
		userRepository.save(user);
	}

	@GetMapping("user/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		log.info(" get -{}", id);
		boolean exists = userRepository.existsById(id);
		System.out.println("Id exists:" + exists);
		if (!exists) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			Optional<User> user = userRepository.findById(id);

			if (user.isPresent()) {
				User userObj = user.get();
				return new ResponseEntity<>(userObj, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	@PostMapping("user/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
			String ln = userService.login(user);
			MessageDTO message = new MessageDTO("success");
			if (ln == "success") {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MessageDTO message = new MessageDTO(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}