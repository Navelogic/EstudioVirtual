package com.github.navelogic.api.DTO;

import com.github.navelogic.api.DTO.User.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudioResponseDTO {
    private String name;
    private String description;
    private Integer audienceReputation;
    private Integer criticReputation;
    private Integer industryReputation;
    private Integer awardsPoints;
    private Integer maxSimultaneousProductions;
    private Integer technologyLevel;
    private BigDecimal budget;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;
    private BigDecimal weeklyOperationalCosts;
    private BigDecimal marketValue;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
    private String categoryByAvailableCapital;
    private UserResponseDTO owner;
}
