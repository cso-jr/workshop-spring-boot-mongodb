package com.carlosoliveira.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosoliveira.workshopmongo.domain.Post;
import com.carlosoliveira.workshopmongo.repository.PostRepository;
import com.carlosoliveira.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repo;
		
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
