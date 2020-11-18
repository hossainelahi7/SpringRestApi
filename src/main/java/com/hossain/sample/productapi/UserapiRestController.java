package com.hossain.sample.productapi;

import java.util.List;

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

	private final UserRepository repository;
	
	public UserapiRestController(UserRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/api")
	String getString() {
		return "Welcome to API world, where everything is possible.";
	}
	
	@GetMapping("/api/user")
	List<User> all(){
		return repository.findAll();
	}
	
	@GetMapping("/api/user/{id}")
	User getUser(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(()-> new UserNotFoudException(id));
	}
	
	@GetMapping("/api/user/search")
	Iterable<User> getUserByName(@RequestParam(value = "first", required = false) String firstName, @RequestParam(value="last", required=false) String lastName) {
		if(firstName != null && lastName!=null)
			return repository.findByFirstNameAndLastName(firstName, lastName);
		else if (firstName!= null)
			return repository.findByfirstName(firstName);
		else if (lastName!=null)
			return repository.findBylastName(lastName);
		else return all();
	}
	
	@PostMapping("/api/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            System.out.println("login requested for: "+ authRequest);
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }
	
	
}
