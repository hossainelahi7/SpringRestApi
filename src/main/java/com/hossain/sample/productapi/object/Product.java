package com.hossain.sample.productapi.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	  private @Id @GeneratedValue Integer productId;
	  private String productName;
	  private String productCode;
	  private String releaseDate;
	  private Double price;
	  private String description;
	  private Double starRating;
	  private String imageUrl;
	  
	  Product(){}
	  
	  
	  
}
