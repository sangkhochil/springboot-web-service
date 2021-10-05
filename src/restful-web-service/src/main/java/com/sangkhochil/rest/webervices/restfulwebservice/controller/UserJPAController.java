package com.sangkhochil.rest.webervices.restfulwebservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.sangkhochil.rest.webervices.restfulwebservice.model.Post;
import com.sangkhochil.rest.webervices.restfulwebservice.model.User;
import com.sangkhochil.rest.webervices.restfulwebservice.repository.PostRepository;
import com.sangkhochil.rest.webervices.restfulwebservice.repository.UserRepository;
import com.sangkhochil.rest.webervices.restfulwebservice.service.UserDaoService;

@RestController
public class UserJPAController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository PostRepository;

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id->"+id);
		
		EntityModel<User> modal = EntityModel.of(user.get());
//		WebMvcLinkBuilder linktoUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers()); // without static import
		WebMvcLinkBuilder linktoUsers = linkTo(methodOn(this.getClass()).retriveAllUsers()); // with static import, static import, import all static methods of class. can call all static method without name of classname
		modal.add(linktoUsers.withRel("all-users"));
		
		return modal;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retriveAllUserPosts(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id->"+id);
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createUserPost(@PathVariable Integer id, @RequestBody Post post) {
		Optional<User> userOp = userRepository.findById(id);
		if(!userOp.isPresent())
			throw new UserNotFoundException("id->"+id);
		
		User user = userOp.get();
		post.setUser(user);
		
		PostRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();		
	}
}
