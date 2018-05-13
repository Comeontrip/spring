package com.rock.springboot.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@RequestMapping("/firstspringboot/welcome")
	public String welcome() {
		return "Weleocme to my first spring boot controller.";
	}
	
	@RequestMapping("/firstspringboot/hello")
	public String hello() {
		return "Hello, Spring Boot.";
	}
	
	@RequestMapping("/firstspringboot/bosszhang")
	public String boss() {
		return "Hello, Spring Boot build for Docker by boss zhang.";
	}
	
	@RequestMapping("/")
	public String helloworld() {
		return "Hello, World with Dockers";
	}


}
