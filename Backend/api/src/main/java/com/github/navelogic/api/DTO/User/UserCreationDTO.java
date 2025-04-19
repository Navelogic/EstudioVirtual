package com.github.navelogic.api.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    @Schema(description = "Username usuário", example = "Navelogic")
    @NotBlank(message = "O username não pode ser vazio.")
    private String username;

    @Schema(description = "Email do usuário", example = "navelogic@email.com")
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O email deve ser válido")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456789QWERTYUIOP")
    @NotBlank(message = "A senha não pode ser vazia")
    @Size(min = 4, message = "A senha deve ter no mínimo 4 caracteres")
    private String password;
}
