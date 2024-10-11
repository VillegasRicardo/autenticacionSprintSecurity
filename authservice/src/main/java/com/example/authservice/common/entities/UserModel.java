package com.example.authservice.common.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Clase que representa el modelo de usuario.
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users", indexes = @Index(name = "users_id_idx", columnList = "id"))
public class UserModel implements UserDetails
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false, unique = true)
	private Long id;
	
	@Column(name="email", updatable = true, nullable = true, unique = false)
	private String email;
	
	@Column(name="password", updatable = true, nullable = true, unique = false)
	private String password;
	
	@Column(name="name", updatable = true, nullable = true, unique = false)
	private String name;
	
	@Column(name="role", updatable = true, nullable = true, unique = false)
	private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	/**
     * Obtiene el nombre de usuario (correo electrónico) del usuario.
     * @return el correo electrónico del usuario.
     */
	@Override
	public String getUsername() {
		return email;
	}
	
	/**
	 * Indica si la cuenta del usuario ha expirado. Una cuenta expirada no puede ser autenticada.
	 * @return <code>true</code> si la cuenta del usuario es válida (es decir, no ha expirado),
	 *         <code>false</code> si ya no es válida (es decir, ha expirado).
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indica si el usuario está bloqueado o desbloqueado. Un usuario bloqueado no puede ser autenticado.
	 * @return <code>true</code> si el usuario no está bloqueado, <code>false</code> en caso contrario.
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indica si las credenciales del usuario (contraseña) han expirado. Las credenciales expiradas
	 * impiden la autenticación.
	 * @return <code>true</code> si las credenciales del usuario son válidas (es decir, no han expirado),
	 *         <code>false</code> si ya no son válidas (es decir, han expirado).
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indica si el usuario está habilitado o deshabilitado. Un usuario deshabilitado no puede ser
	 * autenticado.
	 * @return <code>true</code> si el usuario está habilitado, <code>false</code> en caso contrario.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
