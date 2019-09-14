package com.bits.ns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bits.ns.entities.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>   {

	public Application findApplicationByAppName(String appName);
	
	public Application findApplicationByDomain(String domain);
	
	public Application findApplicationById(long id);
}
