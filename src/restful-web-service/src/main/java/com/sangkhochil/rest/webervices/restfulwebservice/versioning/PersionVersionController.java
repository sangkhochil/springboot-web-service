package com.sangkhochil.rest.webervices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersionVersionController {
	
	//
	//URI based versioning
	//
	@GetMapping(path="v1/person")
	public PersonV1 getPersionV1() {
		return new PersonV1("Charlie Chaplen");
	}
	
	@GetMapping(path="v2/person")
	public PersonV2 getPersionV2() {
		return new PersonV2(new Name("Charlie", "Chaplen"));
	}
	
	//
	//URI param versioning
	//
	@GetMapping(path="/person/params", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Charlie Chaplen");
	}
	
	@GetMapping(path="/person/params", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Charlie", "Chaplen"));
	}
	
	//
	//header versioning
	//
	@GetMapping(path="/person/headers", headers = "X_VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Charlie Chaplen");
	}
	
	@GetMapping(path="/person/headers", headers = "X_VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Charlie", "Chaplen"));
	}
	
	//
	//header accept versioning
	//
	@GetMapping(path="/person/producers", produces = "application/com.sangkhochil.app-v1+json")
	public PersonV1 producerV1() {
		return new PersonV1("Charlie Chaplen");
	}
	
	@GetMapping(path="/person/producers",  produces = "application/com.sangkhochil.app-v2+json")
	public PersonV2 producerV2() {
		return new PersonV2(new Name("Charlie", "Chaplen"));
	}
}
