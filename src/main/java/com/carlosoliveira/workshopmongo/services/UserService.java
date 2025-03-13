package com.carlosoliveira.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.carlosoliveira.workshopmongo.domain.User;
import com.carlosoliveira.workshopmongo.dto.UserDTO;
import com.carlosoliveira.workshopmongo.repository.UserRepository;
import com.carlosoliveira.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
		
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	// lógica da atualização: (1) recuperar o objeto que vai ser alterado; (2) atualizar pelos parâmetros do novo objeto
	public User update(User obj) {
		User newObj = findById(obj.getId()); //(1)
		updateData(newObj, obj); //(2)
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
	
}
