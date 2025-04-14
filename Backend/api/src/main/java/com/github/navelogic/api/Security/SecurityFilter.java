package com.github.navelogic.api.Security;

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


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.clearContext();
        Optional.ofNullable(request.getHeader("Authorization"))
                .flatMap(jwtProvider::validateToken)
                .ifPresent(subject -> {
                    request.setAttribute("user_id", subject);

                    Optional<String> roleOptional = jwtProvider.getRoleFromToken(request.getHeader("Authorization"));

                    List<SimpleGrantedAuthority> authorities = roleOptional
                            .map(role -> Collections.singletonList(new SimpleGrantedAuthority(role)))
                            .orElse(Collections.emptyList());

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(subject, null, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

        filterChain.doFilter(request, response);
    }
}
