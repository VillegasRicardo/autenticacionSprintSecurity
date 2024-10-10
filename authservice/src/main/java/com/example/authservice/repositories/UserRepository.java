package com.example.authservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.authservice.common.entities.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	Optional<UserModel> findByEmail(String email);

}
