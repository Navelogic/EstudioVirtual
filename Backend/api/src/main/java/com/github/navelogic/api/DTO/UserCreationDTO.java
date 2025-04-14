package com.github.navelogic.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private String username;

    @Schema(description = "Email do usuário", example = "navelogic@email.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456789QWERTYUIOP")
    private String password;
}
