package com.hossain.sample.database;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.reflect.TypeToken;
import com.hossain.sample.productapi.object.DataUtils;
import com.hossain.sample.productapi.object.Product;
import com.hossain.sample.productapi.object.User;
import com.hossain.sample.productapi.repository.ProductRepository;
import com.hossain.sample.productapi.repository.UserRepository;


@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initProductDatabase(ProductRepository repository) {
		return args -> {
			List<Product> products;
			DataUtils data = new DataUtils();
			products = data.getJsonObject("data/", "products", new TypeToken<ArrayList<Product>>(){}.getType());
			for( Product product: products) {
				log.info("Preloading " + repository.save(product));				
			}
		};
	}
	
	@Bean
	CommandLineRunner initUserDatabase(UserRepository repository) {
		return args -> {
			List<User> users;
			DataUtils data = new DataUtils();
			users = data.getJsonObject("data/", "users", new TypeToken<ArrayList<User>>(){}.getType());
			for(User user: users) {
				log.info("Preloading "+ repository.save(user));
			}
		};
	}
	
//	public static void main(String[] args) {
//		List<User> users;
//		DataUtils data = new DataUtils();
//		users = data.getJsonObject("data/", "users", new TypeToken<ArrayList<User>>(){}.getType());
//		System.out.println(users);
//	}


}
