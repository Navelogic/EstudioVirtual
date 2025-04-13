package com.github.navelogic.api.Model.Production;

import com.github.navelogic.api.Enum.AgeRatingEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import com.github.navelogic.api.Enum.ProductionStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String tagline;

    @ElementCollection(targetClass = GenreEnum.class)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Set<GenreEnum> genres = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AgeRatingEnum ageRating;

    @Enumerated(EnumType.STRING)
    private ProductionStatusEnum productionStatus = ProductionStatusEnum.CONCEPT;

    private BigDecimal budget;
    private BigDecimal marketingBudget;
    private BigDecimal revenue;

    private LocalDate productionStartDate;
    private LocalDate releaseDate;

    private int qualityScore;
    private int popularityScore;
    private int criticScore;

    private Boolean isAdult = false;



}
