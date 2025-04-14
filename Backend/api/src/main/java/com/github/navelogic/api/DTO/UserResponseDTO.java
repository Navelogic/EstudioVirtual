package com.github.navelogic.api.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "DTO de resposta para informações do usuário")
public class UserResponseDTO {

    @Schema(description = "Username do usuário", example = "Navelogic")
    private String username;

    @Schema(description = "Email do usuário", example = "navelogic@email.com")
    private String email;

    @Schema(description = "Papel/função do usuário no sistema", example = "ROLE_USER",
            allowableValues = {"ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER"})
    private String role;


}
