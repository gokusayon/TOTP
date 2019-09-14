package com.bits.ns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bits.ns.entities.Application;
import com.bits.ns.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	public User findUserByIdAndApplication(Long id, Application application);
	
	public User findUserByUsernameAndApplication(String username, Application app);
	
	public User findUserById(long id);
}
