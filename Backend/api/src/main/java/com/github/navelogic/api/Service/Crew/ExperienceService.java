package com.github.navelogic.api.Service.Crew;

import com.github.navelogic.api.Enum.CrewRoleEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ExperienceService {

    public int calculateExperience(CrewRoleEnum role, GenreEnum specialty, Integer age) {
        Random random = new Random();

        int baseMaxExperience = age - getMinimumEducationAge(role);
        if (baseMaxExperience <= 0) return 0;

        double baseExperienceRatio = 0.5 + (random.nextDouble() * 0.5);
        double roleMultiplier = getRoleExperienceMultiplier(role);
        double specialtyMultiplier = getSpecialtyExperienceMultiplier(specialty);
        double finalExperience = baseMaxExperience * baseExperienceRatio * roleMultiplier * specialtyMultiplier;

        return (int) Math.round(finalExperience);
    }

    private int getMinimumEducationAge(CrewRoleEnum role) {
        return switch (role) {
            case DIRECTOR, CINEMATOGRAPHER -> 22;
            case WRITER, COMPOSER -> 21;
            case VFX_ARTIST, SOUND_DESIGNER -> 20;
            case ACTOR -> 7;
            case MAKEUP_ARTIST, COSTUME_DESIGNER -> 18;
            case STUNT_PERFORMER -> 19;
            default -> 18;
        };
    }

    private double getRoleExperienceMultiplier(CrewRoleEnum role) {
        return switch (role) {
            case DIRECTOR -> 0.85;
            case ACTOR -> 0.95;
            case WRITER -> 0.90;
            case VFX_ARTIST -> 0.98;
            case STUNT_PERFORMER -> 0.80;
            case CINEMATOGRAPHER -> 0.88;
            case COMPOSER -> 0.92;
            case SOUND_DESIGNER -> 0.94;
            case COSTUME_DESIGNER, MAKEUP_ARTIST -> 0.96;
            default -> 0.90;
        };
    }

    private double getSpecialtyExperienceMultiplier(GenreEnum specialty) {
        return switch (specialty) {
            case ACTION, SCIFI -> 0.85;
            case COMEDY, DRAMA -> 1.0;
            case HORROR, THRILLER -> 0.90;
            case ANIMATION -> 0.88;
            case DOCUMENTARY -> 0.95;
            case INDIE -> 1.02;
            case EXPERIMENTAL -> 0.80;
            default -> 0.92;
        };
    }
}
