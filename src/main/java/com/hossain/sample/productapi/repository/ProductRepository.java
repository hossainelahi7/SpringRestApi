package com.hossain.sample.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossain.sample.productapi.object.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
