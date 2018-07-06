package com.assessment.thelightsabershop.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		     .authorizeRequests()
		        .antMatchers("/","/SignUp","/user/**","/Jedisabershop/**","/js/**","/css/**", "/pictures/**" , "/fonts/**").permitAll()
		        .anyRequest().authenticated()
		        .and()  
	         .csrf()
	         	.disable()
		     .formLogin()
			     .loginPage("/login")
	             .permitAll()
                .and()
		    .logout()
		        .permitAll();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }
    
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
        users.put("jedi@master.com","{noop}pass,ROLE_JEDIMASTER,enabled"); //adding for default jedimaster account
        return new InMemoryUserDetailsManager(users);
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
    	UserDetails padavan =
             User.withDefaultPasswordEncoder()
                .username("padavan")
                .password("password")
                .roles("USER")
                .build();
    	UserDetails jedi =
                User.withDefaultPasswordEncoder()
                   .username("jedi@master.com")
                   .password("pass")
                   .roles("USER")
                   .build();
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("password")
                   .roles("ADMIN")
                   .build();
        	return new InMemoryUserDetailsManager(padavan, jedi, admin);
    }*/
    
}