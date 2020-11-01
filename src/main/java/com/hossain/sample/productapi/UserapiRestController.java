package com.hossain.sample.productapi;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hossain.sample.productapi.object.User;
import com.hossain.sample.productapi.repository.UserRepository;

@RestController
public class UserapiRestController {

	private final UserRepository repository;
	
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
	
	@GetMapping("/user/search")
	Iterable<User> getUserByName(@RequestParam("first") String firstName) {
		return repository.findByfirstName(firstName);
	}
}
