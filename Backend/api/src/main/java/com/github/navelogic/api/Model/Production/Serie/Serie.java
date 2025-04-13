package com.github.navelogic.api.Model.Production.Serie;

import com.github.navelogic.api.Model.Production.Production;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Serie extends Production {

    private Boolean isOnGoing = true;
    private Integer totalSeasons;
    private Integer averageEpisodesPerSeason;
    private String networkOrPlatform;

}
