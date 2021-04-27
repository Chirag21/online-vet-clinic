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
public class UserServiceImpl implements UserService{

	UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		
		return null;
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
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean existsUserByUsername(String username) {
		return userRepository.existsUserByUsername(username);
	}
	
	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepository.existsUserByEmail(email);
	}

}
