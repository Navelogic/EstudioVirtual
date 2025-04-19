package com.github.navelogic.api.Service;

import com.github.navelogic.api.DTO.StudioCreationDTO;
import com.github.navelogic.api.DTO.StudioResponseDTO;
import com.github.navelogic.api.DTO.StudioUpdateDTO;
import com.github.navelogic.api.DTO.User.UserResponseDTO;
import com.github.navelogic.api.Enum.UserRoleEnum;
import com.github.navelogic.api.Exception.AccessDeniedException;
import com.github.navelogic.api.Exception.ResourceNotFoundException;
import com.github.navelogic.api.Model.Studio;
import com.github.navelogic.api.Model.UserModel;
import com.github.navelogic.api.Repository.StudioRepository;
import com.github.navelogic.api.Repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Transactional
    public StudioResponseDTO createStudio(StudioCreationDTO studioDTO, UUID userId){
        var studio = new Studio();
        studio.setName(studioDTO.getName());
        studio.setDescription(studioDTO.getDescription());
        studio.setOwner(userService.findById(userId).orElseThrow(() -> new ValidationException("Usuário não encontrado")));

        var savedStudio = this.studioRepository.save(studio);
        UserResponseDTO userResponse = userService.userProfile(userId);

        return getStudioResponseDTO(savedStudio, userResponse);
    }

    @Transactional
    public StudioResponseDTO updateStudio(StudioUpdateDTO studioUpdateDTO, UUID userId) {
        var studio = studioRepository.findByOwnerId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Estúdio não encontrado para o usuário com ID: " + userId));

        validateStudioUpdate(studioUpdateDTO);

        studio.setName(studioUpdateDTO.getName());
        studio.setDescription(studioUpdateDTO.getDescription());

        var updatedStudio = this.studioRepository.save(studio);

        UserResponseDTO userResponse = userService.userProfile(userId);
        return getStudioResponseDTO(updatedStudio, userResponse);
    }

    @Transactional
    public void deactivateStudio(Long studioId, UUID userId) {
        var studio = findStudioById(studioId);
        var user = findUserById(userId);


        boolean isModerator = user.getRole().equals(UserRoleEnum.ROLE_MODERATOR);
        boolean isAdmin = user.getRole().equals(UserRoleEnum.ROLE_ADMIN);

        if (!(isModerator || isAdmin)) {
            throw new ValidationException("Você não tem permissão para desativar este estúdio. Apenas moderadores e administradores podem realizar esta operação.");
        }

        try {
            studio.setIsActive(false);
            studioRepository.save(studio);
        } catch (Exception e) {
            throw new ValidationException("Erro ao desativar estúdio: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteStudio(Long studioId, UUID userId) {

        var studio = findStudioById(studioId);
        var user = findUserById(userId);

        boolean isOwner = studio.getOwner().getId().equals(userId);
        boolean isAdmin = user.getRole().equals(UserRoleEnum.ROLE_ADMIN);

        if (!(isOwner || isAdmin)) {
            throw new ValidationException("Você não tem permissão para excluir este estúdio. Apenas o proprietário ou administradores podem realizar esta operação.");
        }

        UserModel owner = studio.getOwner();
        owner.setStudio(null);
        userRepository.save(owner);

        studioRepository.delete(studio);
    }

    public StudioResponseDTO studioProfile(UUID id) {

        studioRepository.findByOwnerId(id)
                .orElseThrow(() -> new ValidationException("Estúdio não encontrado"));

        var studio = studioRepository.findByOwnerId(id).orElseThrow(() -> new ValidationException("Estúdio não encontrado"));
        var userResponse = userService.userProfile(id);
        return getStudioResponseDTO(studio, userResponse);
    }

    public Optional<Studio> findById(Long id) {
        return this.studioRepository.findById(id);
    }

    private Studio findStudioById(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estúdio não encontrado com ID: " + id));
    }

    private UserModel findUserById(UUID id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    private void validateStudioUpdate(StudioUpdateDTO studioDTO) {
        List<String> errors = new ArrayList<>();

        validateStudioName(studioDTO.getName(), errors);
        validateStudioDescription(studioDTO.getDescription(), errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }

    private void validateStudioName(String name, List<String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.add("Nome do estúdio não pode ser vazio");
        } else if (name.length() < 3) {
            errors.add("Nome do estúdio deve ter no mínimo 3 caracteres");
        } else if (name.length() > 50) {
            errors.add("Nome do estúdio deve ter no máximo 50 caracteres");
        }
    }

    private void validateStudioDescription(String description, List<String> errors) {
        if (description == null || description.trim().isEmpty()) {
            errors.add("Descrição do estúdio não pode ser vazia");
        } else if (description.length() < 10) {
            errors.add("Descrição do estúdio deve ter no mínimo 10 caracteres");
        } else if (description.length() > 30) {
            errors.add("Descrição do estúdio deve ter no máximo 30 caracteres");
        }
    }

    private StudioResponseDTO getStudioResponseDTO(Studio studio, UserResponseDTO userResponse) {
        return StudioResponseDTO.builder()
                .name(studio.getName())
                .description(studio.getDescription())
                .audienceReputation(studio.getAudienceReputation())
                .criticReputation(studio.getCriticReputation())
                .industryReputation(studio.getIndustryReputation())
                .awardsPoints(studio.getAwardsPoints())
                .maxSimultaneousProductions(studio.getMaxSimultaneousProductions())
                .technologyLevel(studio.getTechnologyLevel())
                .budget(studio.getBudget())
                .totalRevenue(studio.getTotalRevenue())
                .totalExpenses(studio.getTotalExpenses())
                .weeklyOperationalCosts(studio.getWeeklyOperationalCosts())
                .marketValue(studio.getMarketValue())
                .createdAt(studio.getCreatedAt())
                .updatedAt(studio.getUpdatedAt())
                .isActive(studio.getIsActive())
                .owner(userResponse).build();
    }

}
