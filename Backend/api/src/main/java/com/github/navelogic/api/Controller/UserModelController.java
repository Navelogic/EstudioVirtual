package com.github.navelogic.api.Controller;

import com.github.navelogic.api.Service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserModelController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getUserProfile(HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        return ResponseEntity.ok(userService.userProfile(UUID.fromString(userId)));
    }

    @GetMapping("/desactivate")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> desactivateUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        userService.desactivateUser(UUID.fromString(userId));
        return ResponseEntity.ok("Usuário desativado com sucesso");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id, HttpServletRequest request) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }

}
