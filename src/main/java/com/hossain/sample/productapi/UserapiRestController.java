package com.hossain.sample.productapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hossain.sample.productapi.object.User;
import com.hossain.sample.productapi.repository.UserRepository;

@RestController
public class UserapiRestController {

	private final UserRepository repository;
	
	@Autowired
	public UserapiRestController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/user")
	List<User> all(){
		return repository.findAll();
	}
	
	@GetMapping("/user/{email}")
	User getUser(@PathVariable String email) {
		return repository.findById(email).orElseThrow(()-> new UserNotFoudException(email));
	}
}
