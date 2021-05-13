package com.onlinevet.clinic.serviceimpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinevet.clinic.model.BaseEntity;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.repository.UserRepository;
import com.onlinevet.clinic.service.RoleService;
import com.onlinevet.clinic.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}

	@Override
	public Set<User> findAll() {
		return userRepository.findAll().stream().collect(Collectors.toSet());
	}

	@Override
	public User save(User user) {
		return userRepository.saveAndFlush(user);
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
		return userRepository.findByUsernameIgnoreCase(username);
	}

	@Override
	public boolean existsUserByUsername(String username) {
		return userRepository.existsUserByUsernameIgnoreCase(username);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepository.existsUserByEmailIgnoreCase(email);
	}

	@Override
	public Optional<User> findByResetPasswordToken(String token) {
		return userRepository.findByResetPasswordToken(token);
	}

	@Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.saveAndFlush(user);
        } else {
            throw new RuntimeException("Error setting password");
        }
    }
    
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token).orElse(User.builder().build());
    }
	
	@Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepository.findByUsernameIgnoreCase(username).orElseThrow();
	}

	@Override
	public User register(BaseEntity baseEntity) {
		User user = (User) baseEntity;
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.addRole(roleService.getDefaultRole());
		return userRepository.saveAndFlush(user);
	}
}
