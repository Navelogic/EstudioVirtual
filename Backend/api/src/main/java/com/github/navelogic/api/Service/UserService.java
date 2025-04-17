package com.github.navelogic.api.Service;

import com.github.navelogic.api.DTO.UserCreationDTO;
import com.github.navelogic.api.DTO.UserResponseDTO;
import com.github.navelogic.api.DTO.UserUpdateDTO;
import com.github.navelogic.api.DTO.UserUpdatePasswordDTO;
import com.github.navelogic.api.Enum.UserRoleEnum;
import com.github.navelogic.api.Exception.ValidationException;
import com.github.navelogic.api.Model.UserModel;
import com.github.navelogic.api.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO createUser(UserCreationDTO userDTO){
        var userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(userDTO.getPassword());

        validateUser(userModel);

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

    @Transactional
    public UserResponseDTO updateUser(UUID id, UserUpdateDTO userUpdateDTO) {
        var user = findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        user.setUsername(userUpdateDTO.getUsername());
        user.setEmail(userUpdateDTO.getEmail());

        var updatedUser = this.userRepository.save(user);

        return UserResponseDTO.builder()
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .role(formatRole(updatedUser.getRole().name()))
                .build();
    }

    @Transactional
    public UserResponseDTO updateUserPassword(UUID id, UserUpdatePasswordDTO userUpdatePasswordDTO) throws ValidationException {
        var user = findById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        if (!passwordEncoder.matches(userUpdatePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new ValidationException("Senha atual incorreta");
        }

        if (userUpdatePasswordDTO.getNewPassword() == null || userUpdatePasswordDTO.getNewPassword().trim().isEmpty() || userUpdatePasswordDTO.getNewPassword().length() < 6) {
            throw new ValidationException("Nova senha deve ter no mínimo 6 caracteres");
        }

        user.setPassword(passwordEncoder.encode(userUpdatePasswordDTO.getNewPassword()));
        var updatedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .username(updatedUser.getUsername())
                .email(updatedUser.getEmail())
                .role(formatRole(updatedUser.getRole().name()))
                .build();
    }

    private Optional<UserModel> findById(UUID id) {
        return userRepository.findById(id);
    }

    private void validateUser(UserModel user) {

        List<String> errors = new ArrayList<>();

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errors.add("Email não pode ser vazio");
        } else if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Email inválido");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.add("Email já cadastrado");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errors.add("Senha não pode ser vazia");
        } else if (user.getPassword().length() < 8) {
            errors.add("Senha deve ter no mínimo 6 caracteres");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
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
