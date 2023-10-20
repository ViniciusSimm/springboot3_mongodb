package com.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	// <Type of data, ID data type>
}
