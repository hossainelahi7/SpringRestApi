package com.hossain.sample.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossain.sample.productapi.object.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	Iterable<User> findByfirstName(String firstName);
	Iterable<User> findBylastName(String lastName);
	Iterable<User> findByFirstNameAndLastName(String firstName, String lastName);
	Iterable<User> findByEmail(String email);
}
