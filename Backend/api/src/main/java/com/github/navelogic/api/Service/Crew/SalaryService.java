package com.github.navelogic.api.Service.Crew;

import com.github.navelogic.api.Enum.CrewRoleEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SalaryService {

    public BigDecimal calculateSalary(
            CrewRoleEnum role,
            GenreEnum specialty,
            int age,
            int experience,
            int popularity,
            int artisticCreativity,
            int productivity
    ) {

        Map<CrewRoleEnum, double[]> roleSalaryRanges = new HashMap<>();
        roleSalaryRanges.put(CrewRoleEnum.DIRECTOR, new double[]{15000.0, 180000.0});
        roleSalaryRanges.put(CrewRoleEnum.WRITER, new double[]{8000.0, 100000.0});
        roleSalaryRanges.put(CrewRoleEnum.ACTOR, new double[]{5000.0, 250000.0});
        roleSalaryRanges.put(CrewRoleEnum.CINEMATOGRAPHER, new double[]{12000.0, 120000.0});
        roleSalaryRanges.put(CrewRoleEnum.EDITOR, new double[]{8000.0, 90000.0});
        roleSalaryRanges.put(CrewRoleEnum.COMPOSER, new double[]{7000.0, 85000.0});
        roleSalaryRanges.put(CrewRoleEnum.SOUND_DESIGNER, new double[]{6000.0, 75000.0});
        roleSalaryRanges.put(CrewRoleEnum.VFX_ARTIST, new double[]{10000.0, 150000.0});
        roleSalaryRanges.put(CrewRoleEnum.COSTUME_DESIGNER, new double[]{5000.0, 70000.0});
        roleSalaryRanges.put(CrewRoleEnum.MAKEUP_ARTIST, new double[]{4000.0, 65000.0});
        roleSalaryRanges.put(CrewRoleEnum.STUNT_PERFORMER, new double[]{6000.0, 80000.0});

        double[] salaryRange = roleSalaryRanges.getOrDefault(role, new double[]{4000.0, 60000.0});
        double ageWeight = calculateAgeWeight(age, role);
        double experienceFactor = Math.pow((double) experience / 40, 1.3) * ageWeight;

        experienceFactor = Math.min(experienceFactor, 1.0);

        double baseSalary = salaryRange[0] + ((salaryRange[1] - salaryRange[0]) * experienceFactor);
        double marketMultiplier = getMarketMultiplier(role, specialty);
        double careerStageMultiplier = getCareerStageMultiplier(age, experience);
        double performanceMultiplier = calculatePerformanceMultiplier(popularity, artisticCreativity, productivity, role);
        double finalSalary = baseSalary * marketMultiplier * careerStageMultiplier * performanceMultiplier;

        Random random = new Random();

        double marketVariation = 0.92 + (random.nextDouble() * 0.16);

        finalSalary *= marketVariation;

        return BigDecimal.valueOf(finalSalary).setScale(2, RoundingMode.HALF_UP);
    }

    private double calculateAgeWeight(int age, CrewRoleEnum role) {
        double peakAge = getPeakAgeForRole(role);
        double ageDiff = Math.abs(age - peakAge);
        return Math.max(0.7, 1.0 - (ageDiff / 100.0));
    }

    private double getPeakAgeForRole(CrewRoleEnum role) {
        return switch (role) {
            case DIRECTOR -> 45.0;
            case ACTOR -> 35.0;
            case STUNT_PERFORMER -> 32.0;
            case VFX_ARTIST -> 35.0;
            case WRITER -> 40.0;
            default -> 38.0;
        };
    }

    private double getMarketMultiplier(CrewRoleEnum role, GenreEnum specialty) {
        double roleMarketDemand = switch (role) {
            case VFX_ARTIST -> 1.4; // Alta demanda atual
            case DIRECTOR -> 1.2;
            case ACTOR -> 1.1;
            case WRITER -> 1.05;
            default -> 1.0;
        };

        double genreMultiplier = switch (specialty) {
            case ACTION, SCIFI -> 1.3;
            case THRILLER, ADVENTURE -> 1.2;
            case COMEDY, ANIMATION -> 1.15;
            case DRAMA, FANTASY -> 1.0;
            case HORROR, DOCUMENTARY -> 0.9;
            case INDIE -> 0.8;
            default -> 1.0;
        };

        return roleMarketDemand * genreMultiplier;
    }

    private double getCareerStageMultiplier(int age, int experience) {
        if (age < 25) return 0.85;
        if (age >= 55 && experience > 25) return 1.4;
        if (age >= 45 && experience > 15) return 1.2;
        return 1.0;
    }
    private double calculatePerformanceMultiplier(
            int popularity,
            int artisticCreativity,
            int productivity,
            CrewRoleEnum role) {

        double popularityWeight = switch (role) {
            case ACTOR, DIRECTOR -> 0.4;
            case WRITER, COMPOSER -> 0.2;
            case VFX_ARTIST, CINEMATOGRAPHER -> 0.3;
            default -> 0.1;
        };

        double creativityWeight = switch (role) {
            case WRITER, DIRECTOR, COMPOSER -> 0.4;
            case VFX_ARTIST, COSTUME_DESIGNER -> 0.3;
            default -> 0.2;
        };

        double productivityWeight = 1.0 - (popularityWeight + creativityWeight);

        return 1.0 + (
                (popularity / 100.0 * popularityWeight) +
                        (artisticCreativity / 100.0 * creativityWeight) +
                        (productivity / 100.0 * productivityWeight)
        );
    }
}
