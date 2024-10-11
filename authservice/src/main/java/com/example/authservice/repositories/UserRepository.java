package com.example.authservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authservice.common.entities.UserModel;

/**
 * Repositorio para la entidad UserModel.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	/**
     * Busca un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario
     * @return un Optional que contiene el UserModel si se encuentra, o vacío si no se encuentra
     */
	Optional<UserModel> findByEmail(String email);

}
