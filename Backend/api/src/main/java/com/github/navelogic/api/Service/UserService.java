package com.github.navelogic.api.Service;

import com.github.navelogic.api.DTO.UserCreationDTO;
import com.github.navelogic.api.DTO.UserResponseDTO;
import com.github.navelogic.api.Enum.UserRoleEnum;
import com.github.navelogic.api.Model.UserModel;
import com.github.navelogic.api.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO createUser(UserCreationDTO userDTO){
        this.userRepository.findByEmail(userDTO.getEmail())
                .ifPresent((user) -> {
                    throw new RuntimeException("Email já cadastrado");
                });

        this.userRepository.findByUsername(userDTO.getUsername())
                .ifPresent((user) -> {
                    throw new RuntimeException("Username já cadastrado");
                });

        var userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        userModel.setRole(UserRoleEnum.ROLE_USER);

        var savedUser = this.userRepository.save(userModel);

        return UserResponseDTO.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(formatRole(savedUser.getRole().name()))
                .build();
    }

    private String formatRole(String role) {
        return switch (role) {
            case "ROLE_ADMIN" -> "Administrador(a)";
            case "ROLE_USER" -> "Usuário(a)";
            case "ROLE_MODERATOR" -> "Moderador(a)";
            default -> "Papel desconhecido";
        };
    }




}
