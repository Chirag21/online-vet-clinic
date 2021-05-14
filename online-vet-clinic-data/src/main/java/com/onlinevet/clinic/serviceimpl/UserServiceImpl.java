package com.onlinevet.clinic.serviceimpl;

import com.onlinevet.clinic.exceptions.RoleNotFoundException;
import com.onlinevet.clinic.exceptions.UserNotFoundException;
import com.onlinevet.clinic.model.Role;
import com.onlinevet.clinic.model.User;
import com.onlinevet.clinic.model.UserRegistrationModel;
import com.onlinevet.clinic.repository.UserRepository;
import com.onlinevet.clinic.service.RoleService;
import com.onlinevet.clinic.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Profile("springdatajpa")
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final RoleService roleService;

	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private final ModelMapper modelMapper;

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
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
        user.setRole(user.getRole());
        userRepository.saveAndFlush(user);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return userRepository.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public User register(UserRegistrationModel userRegistrationModel) {
		User user = modelMapper.map(userRegistrationModel, User.class);
		String encryptedPassword = this.bCryptPasswordEncoder.encode(userRegistrationModel.getPassword());
		user.setPassword(encryptedPassword);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setActive('Y');
		//user.addRole(this.roleService.getDefaultRole());

		// Adding role for owner or vet
/*		if (userRegistrationModel.getAdditionalRole() != null) {
			Role additionalRole = this.roleService.findByAuthority(userRegistrationModel.getAdditionalRole()).orElseThrow(RoleNotFoundException::new);
			user.addRole(additionalRole);
		}*/
		return this.userRepository.saveAndFlush(user);
	}
}
