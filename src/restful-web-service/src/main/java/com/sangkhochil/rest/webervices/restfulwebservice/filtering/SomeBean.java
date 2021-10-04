package com.sangkhochil.rest.webervices.restfulwebservice.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//this is the static filtering
//by two ways
//1. @JsonIgnoreProperties(value = {"age", "password"})
//2. @JsonIgnore by individual fields

//@JsonIgnoreProperties(value = {"age", "password"})
public class SomeBean {
	String name;
	
	@JsonIgnore
	String age;

	@JsonIgnore
	String password;

	public SomeBean(String name, String age, String password) {
		this.name = name;
		this.age = age;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
