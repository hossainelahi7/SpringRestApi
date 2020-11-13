package com.hossain.sample.productapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hossain.sample.productapi.object.User;

import java.util.ArrayList;

@Service
public class UserDetailsRepository implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).iterator().next();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPhone1(), new ArrayList<>());
    }
}
