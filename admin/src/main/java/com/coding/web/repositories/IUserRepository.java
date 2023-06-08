package com.coding.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.web.models.User;

public interface IUserRepository extends CrudRepository<User, Long>{
	 User findByEmail(String email);
	 List<User> findByAdminTrue();
	 List<User> findAll();
	 
}
