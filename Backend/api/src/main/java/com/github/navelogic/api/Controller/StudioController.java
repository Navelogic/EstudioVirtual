package com.github.navelogic.api.Controller;

import com.github.navelogic.api.DTO.StudioCreationDTO;
import com.github.navelogic.api.DTO.StudioResponseDTO;
import com.github.navelogic.api.DTO.StudioUpdateDTO;
import com.github.navelogic.api.Exception.ValidationException;
import com.github.navelogic.api.Service.StudioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RestController
@RequestMapping("/api/studio")
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StudioResponseDTO> createStudio(@RequestBody StudioCreationDTO studioCreationDTO, HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        var studio = studioService.createStudio(studioCreationDTO, UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(studio);
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StudioResponseDTO> updateStudio(@RequestBody StudioUpdateDTO studioUpdateDTO, HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        StudioResponseDTO updatedStudio = studioService.updateStudio(studioUpdateDTO, UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.OK).body(updatedStudio);
    }

    @DeleteMapping("/delete/{studioId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> deleteStudio(@PathVariable Long studioId, HttpServletRequest request) {
        try {
            String userId = (String) request.getAttribute("user_id");
            studioService.deleteStudio(studioId, UUID.fromString(userId));
            return ResponseEntity.ok("Estúdio deletado com sucesso");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getStudioProfile(HttpServletRequest request) {
        String userId = (String) request.getAttribute("user_id");
        return ResponseEntity.ok(studioService.studioProfile(UUID.fromString(userId)));
    }

}
