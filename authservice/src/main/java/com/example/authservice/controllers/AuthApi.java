package com.example.authservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.authservice.common.constants.ApiPathConstants;
import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;

import jakarta.validation.Valid;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
	
	@PostMapping(value = "/register")
	ResponseEntity<TokenResponse> createUser (@RequestBody @Valid UserRequest userRequest);
	
	
	
}
