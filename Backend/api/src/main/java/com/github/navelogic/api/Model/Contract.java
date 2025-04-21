package com.github.navelogic.api.Model;

import com.github.navelogic.api.Enum.ContractTypeEnum;
import com.github.navelogic.api.Model.Production.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "production_id")
    private Production production;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isActive = true;

    private BigDecimal baseSalary;
    private BigDecimal signingBonus;
    private BigDecimal royaltiesPercentage;
    private BigDecimal minimumGuarantee;
    private BigDecimal terminationPenalty;

    @Enumerated(EnumType.STRING)
    private ContractTypeEnum contractType;

    private Integer minimumProductionsRequired;

    private Boolean requiresExclusivity;
    private Boolean includesImageRights;
    private Boolean includesCreditsRequirement;
    private Boolean includesAccommodation;
    private Boolean includesTransportation;
    private Boolean includesInsurance;
    private Boolean includesPerformanceBonus;
    private Boolean requiresStuntDouble;
    private Boolean includesCharacterMerchandising;
    private Boolean requiresWardrobe;




}
