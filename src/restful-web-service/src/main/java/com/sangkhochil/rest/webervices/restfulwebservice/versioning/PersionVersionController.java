package com.sangkhochil.rest.webervices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionVersionController {
	@GetMapping(path="v1/person")
	public PersonV1 getPersionV1() {
		return new PersonV1("Charlin Chaplen");
	}
	
	@GetMapping(path="v2/person")
	public PersonV2 getPersionV2() {
		return new PersonV2(new Name("Charlin", "Chaplen"));
	}
	
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Charlin Chaplen");
	}
	
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Charlin", "Chaplen"));
	}
}
