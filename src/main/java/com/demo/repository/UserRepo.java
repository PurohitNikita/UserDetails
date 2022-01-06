package com.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.demo.model.UserDetailsModel;

public interface UserRepo extends MongoRepository<UserDetailsModel, String>{
	
}
