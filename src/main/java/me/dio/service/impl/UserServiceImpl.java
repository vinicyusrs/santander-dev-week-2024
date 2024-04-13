package me.dio.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.User;
import me.dio.repository.UserRepository;
import me.dio.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User userToCreate) {
		// TODO Auto-generated method stub
		if(userRepository.existsById(userToCreate.getId()) && userToCreate.getId() != null){
			throw new IllegalArgumentException("This User ID already exists.");
		}
		
		if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
			throw new IllegalArgumentException("This Account number already exists.");
		}
		
		return userRepository.save(userToCreate);
	}

}
