package com.github.navelogic.api.Model.Production;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie extends Production {

    private Integer durationMinutes;

    private Boolean isOnlyForTheaters = false;
    private Boolean isOnlyForStreaming = false;

    private Boolean isOnTheaters = false;
    private Boolean isOnStreaming = false;

    private Boolean isCult = false;
    private Boolean isViral = false;

    public Movie(String title, int durationMinutes) {
        super();
        this.setTitle(title);
        this.durationMinutes = durationMinutes;
    }
}
