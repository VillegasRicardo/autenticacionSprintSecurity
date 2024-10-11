package com.example.authservice.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.authservice.repositories.UserRepository;

/**
 * Implementación del servicio de detalles del usuario.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	/**
     * Constructor para UserDetailsServiceImpl.
     * @param userRepository el repositorio de usuarios
     */
	public UserDetailsServiceImpl (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	 /**
     * Carga los detalles del usuario por nombre de usuario (correo electrónico).
     * @param username el nombre de usuario (correo electrónico)
     * @return los detalles del usuario
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("UserDetails user not found"));
	}

}
