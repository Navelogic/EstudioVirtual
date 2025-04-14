package com.github.navelogic.api.Model;

import com.github.navelogic.api.Enum.ContractTypeEnum;
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

    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal salary;

    private BigDecimal signingBonus;

    private Boolean isActive = true;

    private Integer productionQuantity;

    @Enumerated(EnumType.STRING)
    private ContractTypeEnum contractType;


}
