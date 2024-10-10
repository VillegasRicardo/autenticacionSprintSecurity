package com.example.authservice.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;
import com.example.authservice.common.entities.UserModel;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.services.AuthService;
import com.example.authservice.services.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	
	public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}
	
	@Override
	public TokenResponse createUser(UserRequest userRequest) {
		return Optional.of(userRequest)
				.map(this::mapToEntity)
				.map(userRepository::save)
				.map(userCreated -> jwtService.generateToken(userCreated.getId()))
				.orElseThrow(() -> new RuntimeException("Error creating user"));
	}

	private UserModel mapToEntity(UserRequest userRequest) {
		return UserModel.builder()
				.email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role("USER")
                .build();
     }
	
	
}
