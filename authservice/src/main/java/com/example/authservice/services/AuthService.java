package com.example.authservice.services;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;

public interface AuthService {
	
	TokenResponse createUser(UserRequest userRequest);

}
