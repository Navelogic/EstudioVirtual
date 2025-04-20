package com.github.navelogic.api.Model.Production.Serie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seasonNumber;
    private Integer episodeCount;
    private BigDecimal budgetPerEpisode;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

}
