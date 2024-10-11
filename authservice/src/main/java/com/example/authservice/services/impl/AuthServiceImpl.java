package com.example.authservice.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;
import com.example.authservice.common.entities.UserModel;
import com.example.authservice.repositories.UserRepository;
import com.example.authservice.services.AuthService;
import com.example.authservice.services.JwtService;

/**
 * Implementación del servicio de autenticación.
 */
@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final JwtService jwtService;
	
	/**
     * Constructor para AuthServiceImpl.
     * @param userRepository el repositorio de usuarios
     * @param jwtService el servicio JWT
     */
	public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}
	
	/**
	 * Crea un nuevo usuario.
	 * @param userRequest la solicitud de usuario
	 * @return una respuesta con el token de acceso
	 */
	@Override
	public TokenResponse createUser(UserRequest userRequest) {
		return Optional.of(userRequest)
				.map(this::mapToEntity)
				.map(userRepository::save)
				.map(userCreated -> jwtService.generateToken(userCreated.getId()))
				.orElseThrow(() -> new RuntimeException("Error creating user"));
	}

	/**
	 * Mapea una solicitud de usuario a una entidad de usuario.
	 * @param userRequest la solicitud de usuario
	 * @return la entidad de usuario
	 */
	private UserModel mapToEntity(UserRequest userRequest) {
		return UserModel.builder()
				.email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role("USER")
                .build();
     }
	
}
