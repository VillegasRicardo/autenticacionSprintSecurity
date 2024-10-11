package com.example.authservice.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.authservice.common.dto.TokenResponse;
import com.example.authservice.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Implementación del servicio JWT.
 */
@Service
public class JwtServiceImpl implements JwtService {

	private final String secretToken;
	
     /**
     * Constructor para JwtServiceImpl.
     * @param secretToken el token secreto utilizado para firmar los JWT
     */
	public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
		this.secretToken = secretToken;
	}
	
    /**
     * Genera un token JWT para un usuario.
     * @param userId el ID del usuario
     * @return una respuesta con el token de acceso
     */
	@SuppressWarnings("deprecation")
	@Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);
        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.secretToken)
                .compact();

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    /**
     * Obtiene las reclamaciones (claims) de un token JWT.
     * @param token el token JWT
     * @return las reclamaciones del token
     */
	@Override
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(this.secretToken)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

    /**
     * Verifica si un token JWT ha expirado.
     * @param token el token JWT
     * @return <code>true</code> si el token ha expirado, <code>false</code> en caso contrario
     */
	@Override
    public boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrae el ID de usuario de un token JWT.
     * @param token el token JWT
     * @return el ID de usuario
     */
    @Override
    public Integer extractUserId(String token) {
        try {
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }

}
