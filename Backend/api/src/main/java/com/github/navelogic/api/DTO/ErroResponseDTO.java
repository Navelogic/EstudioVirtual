package com.github.navelogic.api.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErroResponseDTO {

    @Schema(description = "Mensagem de erro", example = "Informações inválidas")
    private String message;

    @Schema(description = "Status do erro", example = "400")
    private int status;
}
