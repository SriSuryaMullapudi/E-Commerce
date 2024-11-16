package com.SriSurya.Mullapudi.service;

import java.util.List;

import com.SriSurya.Mullapudi.exception.UserException;
import com.SriSurya.Mullapudi.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public List<User> findAllUsers();
}
