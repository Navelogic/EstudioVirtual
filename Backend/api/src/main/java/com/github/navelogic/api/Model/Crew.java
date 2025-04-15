package com.github.navelogic.api.Model;

import com.github.navelogic.api.Enum.CrewRoleEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import com.github.navelogic.api.Model.Production.Production;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CrewRoleEnum role;

    private Integer fame;
    private Integer talent;

    private BigDecimal baseSalary;

    private Boolean isAvailable;

    private Integer fadiga; // 0-100
    private Integer experience;    // 0-100
    private Integer creativity;    // 0-100
    private Integer leadership;    // 0-100
    private Integer reliability;   // 0-100

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> specialties = new HashSet<>();

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contracts = new HashSet<>();
}