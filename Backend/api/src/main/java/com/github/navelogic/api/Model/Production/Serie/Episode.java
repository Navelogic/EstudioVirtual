package com.github.navelogic.api.Model.Production.Serie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer episodeNumber;
    private Integer durationMinutes;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
