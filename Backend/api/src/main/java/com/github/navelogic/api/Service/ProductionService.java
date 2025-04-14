package com.github.navelogic.api.Service;

import com.github.navelogic.api.Enum.ProductionStatusEnum;
import com.github.navelogic.api.Model.Production.Production;
import com.github.navelogic.api.Repository.MovieRepository;
import com.github.navelogic.api.Repository.ProductionRepository;
import com.github.navelogic.api.Repository.SerieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final ProductionRepository productionRepository;
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;

    public List<Production> findAllProductions() {
        return productionRepository.findAll();
    }

    public Production findProductionById(Long id) {
        return productionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produção não encontrada com ID: " + id));
    }

    @Transactional
    public Production saveProduction(Production production) {
        return productionRepository.save(production);
    }

    @Transactional
    public Production updateProduction(Long id, Production productionDetails) {
        Production production = findProductionById(id);

        // Atualiza apenas os campos não nulos
        if (productionDetails.getTitle() != null) {
            production.setTitle(productionDetails.getTitle());
        }
        if (productionDetails.getDescription() != null) {
            production.setDescription(productionDetails.getDescription());
        }
        if (productionDetails.getTagline() != null) {
            production.setTagline(productionDetails.getTagline());
        }
        if (productionDetails.getGenres() != null && !productionDetails.getGenres().isEmpty()) {
            production.setGenres(productionDetails.getGenres());
        }
        if (productionDetails.getAgeRating() != null) {
            production.setAgeRating(productionDetails.getAgeRating());
        }
        if (productionDetails.getProductionStatus() != null) {
            production.setProductionStatus(productionDetails.getProductionStatus());
        }
        if (productionDetails.getBudget() != null) {
            production.setBudget(productionDetails.getBudget());
        }
        if (productionDetails.getMarketingBudget() != null) {
            production.setMarketingBudget(productionDetails.getMarketingBudget());
        }
        if (productionDetails.getRevenue() != null) {
            production.setRevenue(productionDetails.getRevenue());
        }
        if (productionDetails.getIsAdult() != null) {
            production.setIsAdult(productionDetails.getIsAdult());
        }

        return productionRepository.save(production);
    }

    @Transactional
    public void deleteProduction(Long id) {
        Production production = findProductionById(id);
        productionRepository.delete(production);
    }

    @Transactional
    public Production advanceProductionStatus(Long id) {
        Production production = findProductionById(id);

        switch (production.getProductionStatus()) {
            case CONCEPT:
                production.setProductionStatus(ProductionStatusEnum.PRE_PRODUCTION);
                break;
            case PRE_PRODUCTION:
                production.setProductionStatus(ProductionStatusEnum.PRODUCTION);
                production.setProductionStartDate(LocalDate.now());
                break;
            case PRODUCTION:
                production.setProductionStatus(ProductionStatusEnum.POST_PRODUCTION);
                break;
            case POST_PRODUCTION:
                production.setProductionStatus(ProductionStatusEnum.READY_FOR_RELEASE);
                break;
            case READY_FOR_RELEASE:
                production.setProductionStatus(ProductionStatusEnum.RELEASED);
                production.setReleaseDate(LocalDate.now());
                break;
            default:
                throw new IllegalStateException("Não é possível avançar o status da produção a partir do estado: " + production.getProductionStatus());
        }

        return productionRepository.save(production);
    }

    public BigDecimal calculateTotalCost(Long id) {
        Production production = findProductionById(id);

        BigDecimal budget = production.getBudget() != null ? production.getBudget() : BigDecimal.ZERO;
        BigDecimal marketingBudget = production.getMarketingBudget() != null ? production.getMarketingBudget() : BigDecimal.ZERO;

        return budget.add(marketingBudget);
    }

    public BigDecimal calculateProfit(Long id) {
        Production production = findProductionById(id);

        BigDecimal revenue = production.getRevenue() != null ? production.getRevenue() : BigDecimal.ZERO;
        BigDecimal totalCost = calculateTotalCost(id);

        return revenue.subtract(totalCost);
    }

    public boolean isProfitable(Long id) {
        return calculateProfit(id).compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal calculateROI(Long id) {
        BigDecimal totalCost = calculateTotalCost(id);
        BigDecimal profit = calculateProfit(id);

        if (totalCost.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return profit.divide(totalCost, 4, RoundingMode.HALF_UP);
    }

    @Transactional
    public Production addMarketingBudget(Long id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do orçamento de marketing deve ser positivo");
        }

        Production production = findProductionById(id);

        BigDecimal currentMarketingBudget = production.getMarketingBudget() != null ?
                production.getMarketingBudget() : BigDecimal.ZERO;
        production.setMarketingBudget(currentMarketingBudget.add(amount));

        return productionRepository.save(production);
    }

    @Transactional
    public Production addProductionBudget(Long id, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do orçamento de produção deve ser positivo");
        }

        Production production = findProductionById(id);

        BigDecimal currentBudget = production.getBudget() != null ?
                production.getBudget() : BigDecimal.ZERO;
        production.setBudget(currentBudget.add(amount));

        return productionRepository.save(production);
    }

    @Transactional
    public Production improveProductionQuality(Long id, int investmentLevel) {
        if (investmentLevel < 1 || investmentLevel > 5) {
            throw new IllegalArgumentException("Nível de investimento deve ser entre 1 e 5");
        }

        Production production = findProductionById(id);

        // Cada nível de investimento melhora a qualidade em 5-15 pontos
        int qualityImprovement = 5 * investmentLevel + (int)(Math.random() * 10);
        int newQuality = Math.min(100, production.getQualityScore() + qualityImprovement);
        production.setQualityScore(newQuality);

        return productionRepository.save(production);
    }

    @Transactional
    public Production cancelProduction(Long id) {
        Production production = findProductionById(id);

        // Verifica se a produção já foi lançada
        if (production.getProductionStatus() == ProductionStatusEnum.RELEASED) {
            throw new IllegalStateException("Não é possível cancelar uma produção que já foi lançada");
        }

        production.setProductionStatus(ProductionStatusEnum.CANCELED);
        return productionRepository.save(production);
    }



}
