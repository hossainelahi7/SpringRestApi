package com.hossain.sample.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossain.sample.productapi.object.User;



public interface UserRepository extends JpaRepository<User, String>{

	
}
