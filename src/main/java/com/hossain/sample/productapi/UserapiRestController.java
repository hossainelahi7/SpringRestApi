package com.hossain.sample.productapi;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hossain.sample.productapi.object.AuthRequest;
import com.hossain.sample.productapi.object.User;
import com.hossain.sample.productapi.repository.UserRepository;
import com.hossain.sample.productapi.util.JwtUtil;


@RestController
public class UserapiRestController {
	
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    private AuthRequest auth;

	private final UserRepository repository;
	
	public UserapiRestController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/api")
	String getString() {
		return "Welcome to API world, where everything is possible.";
	}
	
	/*
	 * @GetMapping("/api/user") List<User> all(){ return repository.findAll(); }
	 */
	
	@GetMapping("/api/user")
	User getUser(){
		try {
			return repository.findByEmail(auth.getUsername()).iterator().next();
		}catch(NoSuchElementException e) {
			throw new UserNotFoudException(auth.getUsername());
		}
	}
	
//	@GetMapping("/api/user/{id}")
	User getUser(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(()-> new UserNotFoudException(id));
	}
	
//	@GetMapping("/api/user/search")
	User getUser(@RequestParam(value = "first", required = false) String firstName, 
			@RequestParam(value="last", required=false) String lastName,
			@RequestParam(value="email", required=false) String email) {
		try {
			return getUsers(firstName, lastName, email).iterator().next();
		}catch(NoSuchElementException e) {
			throw new UserNotFoudException(email);
		}
			
	}
	
	Iterable<User> getUsers(String firstName, String lastName, String email){
		if(firstName != null && lastName!=null)
			return repository.findByFirstNameAndLastName(firstName, lastName);
		else if (firstName!= null)
			return repository.findByfirstName(firstName);
		else if (lastName!=null)
			return repository.findBylastName(lastName);
		else if (email!=null)
			return repository.findByEmail(email);
		else return null;

	}
	
	@PostMapping("/api/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		auth = authRequest;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return "Bearer "+ jwtUtil.generateToken(authRequest.getUsername());
    }
	
	
}
