package com.hossain.sample.productapi.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
//	private @Id @GeneratedValue Long id;
    private String first_name;
    private String last_name;
    private String company_name;
    private String address;
    private String city;
    private String county;
    private String state;
    private Integer zip;
    private String phone1;
    private String phone2;
    private @Id String email;
    private String web;	

}
