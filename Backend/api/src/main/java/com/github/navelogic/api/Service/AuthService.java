package com.github.navelogic.api.Service;

import com.github.navelogic.api.DTO.AuthDTO;
import com.github.navelogic.api.DTO.AuthResponseDTO;
import com.github.navelogic.api.Enum.UserRoleEnum;
import com.github.navelogic.api.Model.UserModel;
import com.github.navelogic.api.Provider.JWTProvider;
import com.github.navelogic.api.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Duration TOKEN_EXPIRATION = Duration.ofMinutes(60);

    @Value("${user.issuer}")
    private String USER_ISSUER;

    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthResponseDTO execute(AuthDTO authDTO) throws AuthenticationException {
        validateAuthDTO(authDTO);

        Optional<UserModel> userOpt = userRepository.findByEmail(authDTO.getEmail());

        if (userOpt.isPresent()) {
            return authenticateUser(userOpt.get(), authDTO.getPassword());
        } else {
            throw new AuthenticationException("Email ou senha inválidos.");
        }
    }

    private void validateAuthDTO(AuthDTO authDTO) {
        if (authDTO == null || !StringUtils.hasText(authDTO.getEmail()) || !StringUtils.hasText(authDTO.getPassword())) {
            throw new IllegalArgumentException("Dados de autenticação inválidos.");
        }
    }

    private AuthResponseDTO authenticateUser(UserModel user, String password) throws AuthenticationException {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Email ou senha inválidos.");
        }

        String token = jwtProvider.generateToken(user.getId().toString(), USER_ISSUER, TOKEN_EXPIRATION, user.getRole());
        return createAuthResponse(token, user.getRole());
    }

    private AuthResponseDTO createAuthResponse(String token, UserRoleEnum role) {
        return AuthResponseDTO.builder()
                .acessToken(token)
                .role(String.valueOf(role))
                .build();
    }
}
