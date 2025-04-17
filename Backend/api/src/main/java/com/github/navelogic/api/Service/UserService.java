package com.github.navelogic.api.Service;

import com.github.navelogic.api.DTO.UserCreationDTO;
import com.github.navelogic.api.DTO.UserResponseDTO;
import com.github.navelogic.api.Enum.UserRoleEnum;
import com.github.navelogic.api.Exception.ValidationException;
import com.github.navelogic.api.Model.UserModel;
import com.github.navelogic.api.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO createUser(UserCreationDTO userDTO){
        validateNewUser(userDTO);

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

    public UserResponseDTO userProfile(UUID id) {
        var user = findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(formatRole(user.getRole().name()))
                .build();
    }

    @Transactional
    public void deleteUserById(UUID id) {
        var user = findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        userRepository.delete(user);
    }

    @Transactional
    public void desactivateUser(UUID id){
        var user = findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        user.setIsActive(false);
        userRepository.save(user);
    }

    private Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    private void validateNewUser(UserCreationDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(user -> {
                    throw new ValidationException("Email já cadastrado");
                });

        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(user -> {
                    throw new ValidationException("Username já cadastrado");
                });
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
