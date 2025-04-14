package com.github.navelogic.api.Controller;

import com.github.navelogic.api.DTO.ErroResponseDTO;
import com.github.navelogic.api.DTO.UserCreationDTO;
import com.github.navelogic.api.DTO.UserResponseDTO;
import com.github.navelogic.api.Service.AuthService;
import com.github.navelogic.api.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/create")
    @Tag(name = "Cadastro de usuário", description = "Cadastro de um novo usuário")
    @Operation(summary = "Cadastro de usuário", description = "Realiza o cadastro de um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário", content = @Content(schema = @Schema(implementation = ErroResponseDTO.class)))
    })
    public ResponseEntity<Object> create(@RequestBody UserCreationDTO userCreationDTO){
        try{
            var result = this.userService.createUser(userCreationDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            Map<String, Object> responseBody = new HashMap<>();
            if (Objects.equals(e.getMessage(), "Could not commit JPA transaction")){
                var erroResponseDTO = ErroResponseDTO.builder()
                        .message("Alguma informação não foi preenchida corretamente. Favor conferir os campos e tentar novamente.")
                        .status(400)
                        .build();
                return ResponseEntity.badRequest().body(erroResponseDTO);
            } else {
                responseBody.put("message", e.getMessage());
                responseBody.put("status", 400);
            }
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

}
