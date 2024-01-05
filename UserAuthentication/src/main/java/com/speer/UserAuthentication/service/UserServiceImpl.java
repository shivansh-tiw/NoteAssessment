
package com.speer.UserAuthentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.speer.UserAuthentication.entity.User;
import com.speer.UserAuthentication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public boolean addUser(User user) {
		try {
			if(!userRepository.existsById(user.getUsername())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
				return true;
			}
			else return false;
		}catch(Exception e) {
			System.out.println("Message - " + e);
			return false;
		}
	}
	
	@Override
	public boolean validate(String username, String password) {
		try {
			if(userRepository.existsById(username)) {
				User user =userRepository.findById(username).get();
				return passwordEncoder.matches(password, user.getPassword());
			} else return false;
		}catch(Exception e) {
			System.out.println("Message - " + e);
			return false;
		}	}

}

