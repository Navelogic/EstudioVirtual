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

    private int fame;
    private int talent;

    private BigDecimal baseSalary;

    private boolean isAvailable;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contracts = new HashSet<>();

    @ManyToMany(mappedBy = "crew")
    private Set<Production> productions = new HashSet<>();

    // Atributos específicos que afetam a qualidade das produções
    private int experience;    // 0-100
    private int creativity;    // 0-100
    private int leadership;    // 0-100
    private int reliability;   // 0-100

    // Especialidades do membro (bonificações para certos gêneros)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<GenreEnum> specialties = new HashSet<>();
}