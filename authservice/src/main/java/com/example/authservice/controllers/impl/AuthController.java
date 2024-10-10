package com.example.authservice.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;
import com.example.authservice.controllers.AuthApi;
import com.example.authservice.services.AuthService;

@RestController
public class AuthController implements AuthApi {

	private final AuthService authService; 
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@Override
	public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
		return ResponseEntity.ok(authService.createUser(userRequest));
	}

}
