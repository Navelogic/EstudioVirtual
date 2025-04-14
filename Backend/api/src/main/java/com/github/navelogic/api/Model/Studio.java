package com.github.navelogic.api.Model;

import com.github.navelogic.api.Model.Production.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private Integer reputation; // 0-100
    private BigDecimal budget;
    private Integer studioSize; // 1-5 estrelas representando o tamanho/capacidade

    // Propriedades do estúdio
    private Integer maxSimultaneousProductions;
    private LocalDate foundingDate;
    private String headquarters;
    private String logoUrl;

    private int studioLevel = 1;
    private int experiencePoints = 0;
    private int requiredXpForNextLevel = 1000;

    private int equipmentQuality = 50; // 0-100
    private int staffQuality = 50; // 0-100
    private int marketingCapability = 50; // 0-100
    private int distributionNetwork = 50; // 0-100

    // Especialidades do estúdio (bonificações para certos tipos de produções)
    private boolean specializedInAction = false;
    private boolean specializedInDrama = false;
    private boolean specializedInComedy = false;
    private boolean specializedInHorror = false;
    private boolean specializedInScifi = false;

    // Propriedades financeiras
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    private BigDecimal weeklyOperationalCosts = BigDecimal.ZERO;
    private BigDecimal marketValue = BigDecimal.ZERO;

    // Relações com outras entidades
    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    private Set<Production> productions = new HashSet<>();

    /*
    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;
    */

}
