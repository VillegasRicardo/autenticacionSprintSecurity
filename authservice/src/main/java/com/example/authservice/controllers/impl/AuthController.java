package com.example.authservice.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;
import com.example.authservice.controllers.AuthApi;
import com.example.authservice.services.AuthService;

/**
 * Controlador de autenticación que implementa la interfaz AuthApi.
 */
@RestController
public class AuthController implements AuthApi {

	private final AuthService authService; 
	
	/**
     * Constructor para AuthController.
     * @param authService el servicio de autenticación
     */
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	/**
     * Crea un nuevo usuario.
     * @param userRequest la solicitud de usuario
     * @return una respuesta con el token de acceso
     */
	@Override
	public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
		return ResponseEntity.ok(authService.createUser(userRequest));
	}

	/**
     * Obtiene un usuario por su ID.
     * @param userId el ID del usuario
     * @return una respuesta con el ID del usuario
     */
	@Override
	public ResponseEntity<String> getUser(String userId) {
		return ResponseEntity.ok(userId);
	}

}
