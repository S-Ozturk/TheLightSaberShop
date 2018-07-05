package com.assessment.thelightsabershop.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class SimpleSecurityController {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public SimpleSecurityController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @RequestMapping("exists/{username}")
    public boolean userExists(@PathVariable("username") String username ) {
        return inMemoryUserDetailsManager.userExists(username);
    }

    public void add(String username, String password) {
    	inMemoryUserDetailsManager.createUser(User.withDefaultPasswordEncoder().username(username).password(password).roles("USER").build());
        System.out.println("Add user inMemoryUserDetailsManager is working for - " + username + "- password : "+ password);
    }
}