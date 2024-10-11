package com.example.authservice.services;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.common.dto.UserRequest;

/**
 * Interfaz para el servicio de autenticación.
 */
public interface AuthService {
	
	/**
     * Crea un nuevo usuario.
     * @param userRequest la solicitud de usuario
     * @return una respuesta con el token de acceso
     */
	TokenResponse createUser(UserRequest userRequest);

}
