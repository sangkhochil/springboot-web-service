package com.sangkhochil.rest.webervices.restfulwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangkhochil.rest.webervices.restfulwebservice.model.User;
import com.sangkhochil.rest.webervices.restfulwebservice.service.UserDaoService;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> retriveAllUsers(){
		return service.getAllUser();
	}
}
