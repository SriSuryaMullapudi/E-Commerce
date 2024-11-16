package com.SriSurya.Mullapudi.service;

import java.util.List;
import java.util.Optional;

import com.SriSurya.Mullapudi.exception.UserException;
import com.SriSurya.Mullapudi.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.SriSurya.Mullapudi.config.JwtProvider;
import com.SriSurya.Mullapudi.model.User;

@Service
public class UserServiceImplementation implements UserService{

	private UserRepository userRepository;
	private JwtProvider jwtTokenProvider;
	
	public UserServiceImplementation(UserRepository userRepository,JwtProvider jwtTokenProvider) {
		
		this.userRepository=userRepository;
		this.jwtTokenProvider=jwtTokenProvider;
		
	}
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()){
			return user.get();
		}
		throw new UserException("user not found with id "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		System.out.println("user service");
		String email=jwtTokenProvider.getEmailFromJwtToken(jwt);
		
		System.out.println("email"+email);
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("User not exist with email "+email);
		}
		System.out.println("email user"+user.getEmail());
		return user;
	}
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAllByOrderByCreatedAtDesc();
	}

}
