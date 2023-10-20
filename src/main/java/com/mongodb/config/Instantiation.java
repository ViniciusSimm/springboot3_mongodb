package com.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mongodb.domain.Post;
import com.mongodb.domain.User;
import com.mongodb.domain.dto.AuthorDTO;
import com.mongodb.repository.PostRepository;
import com.mongodb.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		
		// Save users first - create ID value
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null,sdf.parse("21/03/2018"),"title","Text",new AuthorDTO(maria));
		Post p2 = new Post(null,sdf.parse("22/04/2019"),"title","Text",new AuthorDTO(alex));
		
		postRepository.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1));
		alex.getPosts().addAll(Arrays.asList(p2));
		
		userRepository.saveAll(Arrays.asList(maria,alex));
		
	}

}
