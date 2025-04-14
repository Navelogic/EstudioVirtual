package com.github.navelogic.api.Provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.navelogic.api.Enum.UserRoleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class JWTProvider {

    @Value("${provider.key}")
    private String key;

    public Optional<String> validateToken(String token){
        try {
            String cleanToken = token.replace("Bearer ", "");
            String issuer = JWT.decode(cleanToken).getIssuer();
            Algorithm algorithm = Algorithm.HMAC256(key);

            return Optional.ofNullable(
                    JWT.require(algorithm)
                            .withIssuer(issuer)
                            .build()
                            .verify(cleanToken)
                            .getSubject()
            );
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public Optional<String> getRoleFromToken(String token){
        try {
            String cleanToken = token.replace("Bearer ", "");
            String role = JWT.decode(cleanToken).getClaim("role").asString();
            return Optional.ofNullable(role);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public String generateToken(String subject, String issuer, Duration expiration, UserRoleEnum role){
        Algorithm algorithm = Algorithm.HMAC256(key);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withClaim("role", role.name())
                .withExpiresAt(java.time.Instant.now().plus(expiration))
                .sign(algorithm);
    }
}
