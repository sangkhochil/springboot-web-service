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
}
