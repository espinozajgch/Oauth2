package com.espinozajg.oauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

	@RequestMapping("greetings")
	@PreAuthorize("hasAnyRol(ROL)")
	public String greetings(@RequestParam(value="name", defaultValue="World") String name) {
		return "Hello "+name+"!";
	}
}
