package com.onlinevet.clinic.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.repositories.UserRepository;
import com.onlinevet.clinic.services.UserService;

@Service
@Profile("springdatajpa")
public class UserServiceJpa implements UserService{

	UserRepository userRepository;
	
	public UserServiceJpa(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public Set<User> findAll() {
		HashSet<User> users = new HashSet<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);		
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
