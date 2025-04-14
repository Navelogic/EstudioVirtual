package com.github.navelogic.api.Service;

import com.github.navelogic.api.Model.Production.Production;
import com.github.navelogic.api.Model.Studio;
import com.github.navelogic.api.Repository.StudioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;

    public List<Studio> findAllStudios() {
        return studioRepository.findAll();
    }

    public Studio findStudioById(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estúdio não encontrado com ID: " + id));
    }

    @Transactional
    public Studio saveStudio(Studio studio) {
        return studioRepository.save(studio);
    }

    @Transactional
    public void deleteStudio(Long id) {
        Studio studio = findStudioById(id);
        studioRepository.delete(studio);
    }

    public Set<Production> getStudioProductions(Long id) {
        Studio studio = findStudioById(id);
        return studio.getProductions();
    }

    public BigDecimal getTotalStudioValue(Long id) {
        Studio studio = findStudioById(id);

        return studio.getProductions().stream()
                .map(Production::getRevenue)
                .filter(Objects::nonNull)
                .reduce(studio.getBudget(), BigDecimal::add);
    }

}
