package com.speer.UserAuthentication.service;

import com.speer.UserAuthentication.entity.User;

public interface UserService {
	public boolean addUser(User user);
	public boolean validate(String username, String password);
}