package com.example.authservice.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.authservice.repositories.UserRepository;
import com.example.authservice.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de autenticación JWT que se ejecuta una vez por solicitud.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
    private final UserRepository userRepository;

    /**
     * Constructor para JwtAuthFilter.
     * @param jwtService      el servicio JWT para manejar tokens
     * @param userRepository  el repositorio de usuarios para buscar detalles del usuario
     */
    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

     /**
     * Filtra las solicitudes HTTP para autenticar usuarios basados en JWT.
     * @param request     la solicitud HTTP
     * @param response    la respuesta HTTP
     * @param filterChain la cadena de filtros
     * @throws ServletException en caso de error de servlet
     * @throws IOException      en caso de error de E/S
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Optional.ofNullable(request.getHeader("Authorization"))
                .filter(header -> !header.isBlank())
                .map(header -> header.substring(7))
                .map(jwtService::extractUserId)
                .flatMap(userId -> userRepository.findById(Long.valueOf(userId)))
                .ifPresent(userDetails -> {
                    request.setAttribute("X-User-Id", userDetails.getId());
                    processAuthentication(request, userDetails);
                });

        filterChain.doFilter(request, response);
    }

    /**
     * Procesa la autenticación del usuario basado en JWT.
     * @param request     la solicitud HTTP
     * @param userDetails los detalles del usuario autenticado
     */
	private void processAuthentication(HttpServletRequest request, UserDetails userDetails) {
		String jwtToken = request.getHeader("Authorization").substring(7);
		Optional.of(jwtToken)
		.filter(token -> !jwtService.isExpired(token))
		.ifPresent(token -> {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			  userDetails, null, userDetails.getAuthorities()
		    );
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		});		
	}
	
}
