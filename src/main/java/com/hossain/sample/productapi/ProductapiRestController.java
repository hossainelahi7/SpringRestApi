package com.hossain.sample.productapi;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hossain.sample.productapi.object.Product;
import com.hossain.sample.productapi.repository.ProductRepository;

@RestController
public class ProductapiRestController {

	private final ProductRepository repository;

	ProductapiRestController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/api/product")
	List<Product> all() {
		return repository.findAll();
	}

	@PostMapping("/api/product")
	Product newProduct(@RequestBody Product newProduct) {
		return repository.save(newProduct);
	}

	@GetMapping("/api/product/{id}")
	Product one(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@PutMapping("/api/product/{id}")
	  Product replaceEmployee(@RequestBody Product newProduct, @PathVariable Integer id) {
	    return repository.findById(id)
	    		.map(product -> {
	    			product.setProductName(newProduct.getProductName());
	    			product.setProductCode(newProduct.getProductCode());
	    			product.setDescription(newProduct.getDescription());
	    			product.setPrice(newProduct.getPrice());
	    			product.setReleaseDate(newProduct.getReleaseDate());
	    			product.setStarRating(newProduct.getStarRating());
	    			product.setImageUrl(newProduct.getImageUrl());
	    			return repository.save(product);
	    		})
	      .orElseGet(() -> {
	        return repository.save(newProduct);
	      });
	  }

	@DeleteMapping("/api/product/{id}")
	void deleteEmployee(@PathVariable Integer id) {
		repository.deleteById(id);
	}

}
