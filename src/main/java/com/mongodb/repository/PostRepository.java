package com.mongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	// <Type of data, ID data type>
	
	
	// From spring data MongoDB
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") // ?0 (first argument) 'i' (case insensitive)
	List<Post> searchTitle(String text);
	
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSeach(String text, Date minDate, Date maxDate);
	
}
