package com.sangkhochil.rest.webervices.restfulwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilteringController {
	
	@GetMapping(path = "/dynamic-filtering-1")
	public MappingJacksonValue getFilteredBean_001() {
		DynamicSomeBean someBean = new DynamicSomeBean("field-1", "field-2", "field-3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicSomeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping(path = "/dynamic-filtering-2")
	public MappingJacksonValue getFilteredBean_002() {
		DynamicSomeBean someBean = new DynamicSomeBean("field-1", "field-2", "field-3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field3","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicSomeBean", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
