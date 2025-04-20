package com.github.navelogic.api.Model;

import com.github.navelogic.api.Enum.CrewRoleEnum;
import com.github.navelogic.api.Enum.GenderEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import com.github.navelogic.api.Enum.PersonalityTraitEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Crew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    private CrewRoleEnum role;

    @Enumerated(EnumType.STRING)
    private GenreEnum specialtie;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<PersonalityTraitEnum> personalityTraits = new HashSet<>();

    private Integer popularity;
    private Integer artisticCreativity;
    private Integer experience;
    private Integer productivity;
    private Integer stress;

    private Boolean isAdult;
    private Boolean isDead = false;
    private Boolean isOnVacation;
    private Boolean isAvailable;

    private LocalDate birthDate;
    private LocalDate deathDate;
    private LocalDate vacationStartDate;
    private LocalDate vacationEndDate;

    private BigDecimal baseSalary;

    @OneToMany(mappedBy = "crew", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contract> contracts = new HashSet<>();

}