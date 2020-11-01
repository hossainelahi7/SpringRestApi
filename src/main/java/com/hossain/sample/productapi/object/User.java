package com.hossain.sample.productapi.object;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    private String firstName;
    private String lastName;
    private String companyName;
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
