package com.github.navelogic.api.Controller;

import com.github.navelogic.api.DTO.*;
import com.github.navelogic.api.Service.AuthService;
import com.github.navelogic.api.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/create")
    @Tag(name = "Cadastro de usuário")
    @Operation(summary = "Cadastro de usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErroResponseDTO.class)))
    })
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        return ResponseEntity.ok(userService.createUser(userCreationDTO));
    }

    @PostMapping("/login")
    @Tag(name = "Login de usuário")
    @Operation(summary = "Login de usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AuthResponseDTO.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErroResponseDTO.class)))
    })
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthDTO authDTO) throws AuthenticationException {
        return ResponseEntity.ok(authService.execute(authDTO));
    }
}
