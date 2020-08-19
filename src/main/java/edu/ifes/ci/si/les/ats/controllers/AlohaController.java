package edu.ifes.ci.si.les.ats.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class AlohaController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String hello() {
		return "Hello! The Service Is Working! :)";
	}

}
