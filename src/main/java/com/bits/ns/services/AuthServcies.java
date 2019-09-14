package com.bits.ns.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bits.ns.entities.Application;
import com.bits.ns.entities.User;
import com.bits.ns.repositories.ApplicationRepository;
import com.bits.ns.repositories.UserRepository;

@Service
public class AuthServcies {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Cacheable(value = "applications", key = "#domain")
	public Application getApplicationByDomain(String domain) {
		logger.debug("Domain:{}", domain);
		return applicationRepository.findApplicationByDomain(domain);
	}
	
	public Application getApplicationByAppName(String domain) {
		logger.debug("Domain:{}", domain);
		return applicationRepository.findApplicationByDomain(domain);
	}
	

	public User getUserByUsernameAndApplication(String userName, String domain) {
		logger.debug("UserName:{}, Tenant:{}", userName, domain);
		Application app = applicationRepository.findApplicationByDomain(domain);
		return userRepository.findUserByUsernameAndApplication(userName, app);
	}
	
	public Application getApplicationById(long id) {
		return applicationRepository.findApplicationById(id);
	}
	
	public User getUserById(long id) {
		return userRepository.findUserById(id);
	}
	
	public User saveUser(User user) {
		Application app = applicationRepository.findApplicationByDomain(user.getApplication().getDomain());
		
		if(app ==null) {
			throw new RuntimeException("No application with this domain name exists.");
		}
		user.setApplication(app);
		return userRepository.save(user);
	}
	
	public Application saveApplication(Application application) {
		return applicationRepository.save(application);
	}
}
