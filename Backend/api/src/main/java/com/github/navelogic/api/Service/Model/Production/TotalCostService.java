package com.github.navelogic.api.Service.Model.Production;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TotalCostService {
    public BigDecimal calculateTotalCost(BigDecimal budget, BigDecimal marketingBudget) {
        if (budget == null || marketingBudget == null) {
            throw new IllegalArgumentException("Budget and Marketing Budget cannot be null");
        }
        return budget.add(marketingBudget);
    }
}
