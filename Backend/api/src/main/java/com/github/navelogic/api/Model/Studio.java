package com.github.navelogic.api.Model;

import com.github.navelogic.api.Model.Production.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    // Reputação
    private Integer audienceReputation = 0;
    private Integer criticReputation = 0;
    private Integer industryReputation = 0;
    private Integer awardsPoints = 0;

    private Integer maxSimultaneousProductions = 1;
    private Integer technologyLevel = 0;

    // Propriedades financeiras
    private BigDecimal budget = BigDecimal.valueOf(500000);
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private BigDecimal weeklyOperationalCosts = BigDecimal.ZERO;
    private BigDecimal marketValue = BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    private Boolean isActive = true;

    // Relações com outras entidades
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    private Set<Production> productions = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel owner;
}
