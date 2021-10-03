package com.sangkhochil.rest.webervices.restfulwebservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sangkhochil.rest.webervices.restfulwebservice.controller.customexception.UserNotFoundException;
import com.sangkhochil.rest.webervices.restfulwebservice.model.User;
import com.sangkhochil.rest.webervices.restfulwebservice.service.UserDaoService;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {
		return service.getAllUser();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id->"+id);
		
		EntityModel<User> modal = EntityModel.of(user);
//		WebMvcLinkBuilder linktoUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers()); // without static import
		WebMvcLinkBuilder linktoUsers = linkTo(methodOn(this.getClass()).retriveAllUsers()); // with static import, static import, import all static methods of class. can call all static method without name of classname
		modal.add(linktoUsers.withRel("all-users"));
		
		return modal;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.addUser(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null)
			throw new UserNotFoundException("id->"+id);
	}
}
