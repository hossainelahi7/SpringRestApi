package com.hossain.sample.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossain.sample.productapi.object.User;



public interface UserRepository extends JpaRepository<User, String>{
	Iterable<User> findByfirstName(String firstName);
	Iterable<User> findBylastName(String lastName);
	Iterable<User> findByfirstNameAndlastName(String firstName, String lastName);
}
