package com.tts.UsersAPI.controller;



import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tts.UsersAPI.model.User;
import com.tts.UsersAPI.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;

		
		
	
	@GetMapping("/users")
		public ResponseEntity <List<User>> getUsers(@RequestParam(value="state", required= false) String state){
			if(state != null) {
				return new ResponseEntity<>(repository.findByState(state), HttpStatus.OK);
				}
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
			}
			
			
		
	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable(value = "id") Long id) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
		repository.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user, BindingResult bindingresult){
				if (!repository.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else if(bindingresult.hasErrors()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else {
				repository.save(user);
				return new ResponseEntity<>(HttpStatus.OK);
			}
	
	}

	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
		if (!repository.findById(id).isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/all")
	public void all() {
		
	}

}

