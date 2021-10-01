package com.sangkhochil.rest.webervices.restfulwebservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sangkhochil.rest.webervices.restfulwebservice.model.User;

@Component
public class UserDaoService {
	private static List<User> list = new ArrayList<>();
	private static int countUser = 3;
	static {
		list.add(new User(1, "John", new Date()));
		list.add(new User(2, "Michel", new Date()));
		list.add(new User(3, "Don", new Date()));
	}
	
	public List<User> getAllUser(){
		return new ArrayList<User>(list);
	}
	
	public User addUser(User user) {
		if(user.getId() == null)
			user.setId(++countUser);
		list.add(user);
		return user;
	}
	
	public User findOne(int id) {
		return list.stream().filter(l -> l.getId() == id).findFirst().get();
	}
}