package com.example.authservice.services;

import com.example.authservice.common.dto.TokenResponse;

import io.jsonwebtoken.Claims;

/**
 * Interfaz para el servicio JWT.
 */
public interface JwtService {

	/**
     * Genera un token JWT para un usuario dado.
     * @param userId el ID del usuario
     * @return una respuesta con el token de acceso
     */
	TokenResponse generateToken(Long userId);

     /**
     * Obtiene las reclamaciones (claims) de un token JWT.
     * @param token el token JWT
     * @return las reclamaciones del token
     */
	Claims getClaims(String token);

     /**
     * Verifica si un token JWT ha expirado.
     * @param token el token JWT
     * @return <code>true</code> si el token ha expirado, <code>false</code> en caso contrario
     */
	boolean isExpired(String token);

	/**
     * Extrae el ID del usuario de un token JWT.
     *
     * @param token el token JWT
     * @return el ID del usuario
     */
	Integer extractUserId(String token);
	
}
