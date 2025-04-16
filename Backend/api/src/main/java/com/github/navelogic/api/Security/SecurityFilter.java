package com.github.navelogic.api.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.navelogic.api.DTO.ErroResponseDTO;
import com.github.navelogic.api.Provider.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.clearContext();

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null){
            try {
                Optional<String> subject = jwtProvider.validateToken(authHeader);
                if (subject.isPresent()) {
                    request.setAttribute("user_id", subject.get());

                    Optional<String> roleOptional = jwtProvider.getRoleFromToken(authHeader);
                    List<SimpleGrantedAuthority> authorities = roleOptional
                            .map(role -> Collections.singletonList(new SimpleGrantedAuthority(role)))
                            .orElse(Collections.emptyList());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(subject.get(), null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                    return;
                }
                sendUnauthorizedError(response);
                return;
            } catch (Exception e) {
                sendUnauthorizedError(response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorizedError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        var erro = ErroResponseDTO.builder()
                .message("Token inválido ou expirado")
                .details(List.of())
                .status(401)
                .build();

        objectMapper.writeValue(response.getWriter(), erro);
    }
}
