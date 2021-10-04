package com.sangkhochil.rest.webervices.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	@GetMapping(path = "/filtering")
	public SomeBean getFilteredBean() {
		return new SomeBean("name-1", "age-1", "password-1");
	}
	
	@GetMapping(path = "/filtering-list")
	public List<SomeBean> getFilteredBeanList() {
		return Arrays.asList(new SomeBean("name-1", "age-1", "password-1"), 
				new SomeBean("name-11", "age-11", "password-11"));
	}
}
