package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	   @Autowired
	    private UserRepository userRepository;

	   @PostMapping
	   public User createUser(@RequestBody User user) {
		   return userRepository.save(user);
	   }
	   @GetMapping
	   public List<User> getAllUsers(){
		   return userRepository.findAll();
	   }
	   
	   @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable String id) {
	        return userRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	   }
	   
	   @PutMapping("/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails) {
	        return userRepository.findById(id).map(user -> {
	            user.setName(userDetails.getName());
	            user.setEmail(userDetails.getEmail());
	            User updatedUser = userRepository.save(user);
	            return ResponseEntity.ok(updatedUser);
	        }).orElse(ResponseEntity.notFound().build());
	    }
	   
	   @DeleteMapping("/{id}")
	   public ResponseEntity<Object> deleteUser(@PathVariable String id) {
	       return userRepository.findById(id).map(user -> {
	           userRepository.delete(user);
	           return ResponseEntity.noContent().build(); // This is ResponseEntity<Void>
	       }).orElse(ResponseEntity.notFound().build()); // This is also ResponseEntity<Void>
	   }
	   
	  
}
