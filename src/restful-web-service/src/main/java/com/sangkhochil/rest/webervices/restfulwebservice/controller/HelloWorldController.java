package com.sangkhochil.rest.webervices.restfulwebservice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sangkhochil.rest.webervices.restfulwebservice.common.Utils;
import com.sangkhochil.rest.webervices.restfulwebservice.model.HelloWorldBean;

import io.micrometer.core.ipc.http.HttpSender.Request;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;

	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world") //-----> shorter form for specific request methods
	public String helloWorld() {
		return "Hello-World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World-Bean");
	}
	
	@GetMapping(path = "/hello-world/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello-World: %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader( name = "Accept-Language", required = false) Locale locale
			) {
		
		//return "Hello-World";
		//en = Hello World
		//nl = Goede Morgen
		//fr = Bonjour
		
		//return messageSource.getMessage(Utils.GOOD_MORNING_MESSAGE, null, "Default Message", locale);
		return messageSource.getMessage(Utils.GOOD_MORNING_MESSAGE, null, LocaleContextHolder.getLocale()); // if client not select Accept-Language, server return default english message
	}
	
	@GetMapping(path = "/hello-world-request-params")
	public String helloWorldRequestParam(@RequestParam(value = "name", defaultValue = "default value") String name) {
		return name;
	}
	
	@GetMapping(path = "/hello-world-request-params-1", params = "check1=1")
	public String helloWorldRequestParam_1() {
		return "helloWorldRequestParam_1";
	}
}
