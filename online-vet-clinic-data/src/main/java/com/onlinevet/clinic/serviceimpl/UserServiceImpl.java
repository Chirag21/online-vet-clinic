package com.onlinevet.clinic.serviceimpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.repositories.UserRepository;
import com.onlinevet.clinic.services.UserService;

@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent())
			return user.get();

		return null;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
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

	@Override
	public Optional<User> findByResetPasswordToken(String token) {
		return userRepository.findByResetPasswordToken(token);
	}

	@Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Error setting password");
        }
    }
    
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElse(User.builder().build());
    }
	
	@Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
	
}
