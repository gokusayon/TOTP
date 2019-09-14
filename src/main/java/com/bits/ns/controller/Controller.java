
package com.bits.ns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bits.ns.entities.Application;
import com.bits.ns.entities.User;
import com.bits.ns.services.AuthServcies;
import com.bits.ns.services.CommonServices;
import com.bits.ns.services.TOTPService;

@RestController
@EnableAutoConfiguration
public class Controller {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static boolean isFirstTime = true;

	@Autowired
	TOTPService totpService;

	@Autowired
	AuthServcies authServcies;

	@Autowired
	CommonServices commonServices;

	@RequestMapping("/test")
	String login() {
		return "Test Successfull!";
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleException(Exception e) {
		logger.error("Validation Exception occured : " + e.getMessage());
		return e.getMessage();
	}

	@RequestMapping(value = "/get/user", method = RequestMethod.GET)
	User getUserByUsername(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "domain", required = true) String domain) {
		return authServcies.getUserByUsernameAndApplication(username, domain);
	}

	@RequestMapping(value = "/get/application", method = RequestMethod.GET)
	Application getApplicationByDomain(@RequestParam(value = "domain", required = true) String domain) {
		return authServcies.getApplicationByDomain(domain);
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	String addUser(@RequestBody User user) {
		authServcies.saveUser(user);
		return "{\"success\":true}";
	}

	@RequestMapping(value = "/addApplication", method = RequestMethod.POST)
	String addApplication(@RequestBody Application app) throws RuntimeException {
		authServcies.saveApplication(app);
		return "{\"success\":true}";
	}

	@RequestMapping("/auth")
	String generateTotpByUserId(@RequestParam(value = "id") long id) {
		return commonServices.getTotp(id);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/generate")
	String generateTotpByUserAndApplication(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "domain", required = true) String domain) {
		return commonServices.getTotp(username, domain);
	}

	@RequestMapping("/compare")
	boolean compare(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "domain", required = true) String domain,
			@RequestParam(value = "totp", required = true) String totp) {
		return commonServices.compare(username, domain, totp);
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	boolean verify() {
		return true;
	}

	/*
	 * @RequestMapping(value = "/get/application/i", method = RequestMethod.GET)
	 * Application getApplicationById(@RequestParam(value = "id", required = true)
	 * long id) { return authServcies.getApplicationById(id); }
	 * 
	 * @RequestMapping(value = "/get/user/i", method = RequestMethod.GET) User
	 * getUserById(@RequestParam(value = "id", required = true) long id) { return
	 * authServcies.getUserById(id); }
	 */

}
