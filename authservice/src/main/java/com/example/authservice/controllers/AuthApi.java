package com.example.authservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.authservice.common.constants.ApiPathConstants;
import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;

import jakarta.validation.Valid;

/**
 * Interfaz para la API de autenticación.
 */
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
	
	 /**
     * Crea un nuevo usuario.
     * @param userRequest la solicitud de usuario
     * @return una respuesta con el token de acceso
     */
	@PostMapping(value = "/register")
	ResponseEntity<TokenResponse> createUser (@RequestBody @Valid UserRequest userRequest);	
	
	 /**
     * Obtiene un usuario por su ID.
     * @param userId el ID del usuario
     * @return una respuesta con el ID del usuario
     */
	@GetMapping
	ResponseEntity<String> getUser(@RequestAttribute(name = "X-User-Id") String userId);
	
}

