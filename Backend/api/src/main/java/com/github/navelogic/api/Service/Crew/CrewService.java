package com.github.navelogic.api.Service.Crew;

import com.github.navelogic.api.Enum.CrewRoleEnum;
import com.github.navelogic.api.Enum.GenderEnum;
import com.github.navelogic.api.Enum.GenreEnum;
import com.github.navelogic.api.Enum.PersonalityTraitEnum;
import com.github.navelogic.api.Model.Crew;
import com.github.navelogic.api.Repository.CrewRepository;
import com.github.navelogic.api.Util.NameGenerationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;
    private final NameGenerationUtil nameGenerationUtil;
    private final ExperienceService experienceService;
    private final SalaryService salaryService;

    public void generateRandomCrews() {
        List<Crew> crews = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Set<PersonalityTraitEnum> traits = generateRandomPersonalities();
            CrewRoleEnum role = CrewRoleEnum.values()[random.nextInt(CrewRoleEnum.values().length)];
            LocalDate birthDate = generateRandomBirthDate(role);
            GenderEnum gender = GenderEnum.values()[random.nextInt(GenderEnum.values().length)];

            GenreEnum specialty = GenreEnum.values()[random.nextInt(GenreEnum.values().length)];

            int age = Period.between(birthDate, LocalDate.now()).getYears();
            int experience = experienceService.calculateExperience(role, specialty, age);
            int popularity = random.nextInt(101);
            int artisticCreativity = random.nextInt(101);
            int productivity = random.nextInt(101);

            BigDecimal baseSalary = salaryService.calculateSalary(role, specialty, age, experience, popularity, artisticCreativity, productivity);


            Crew crew = Crew.builder()
                    .name(nameGenerationUtil.generateCrewName(gender.name()))
                    .gender(gender)
                    .role(role)
                    .specialtie(specialty)
                    .personalityTraits(traits)
                    .popularity(popularity)
                    .artisticCreativity(artisticCreativity)
                    .experience(experience)
                    .productivity(productivity)
                    .stress(random.nextInt(101))
                    .isAdult(true)
                    .isDead(false)
                    .isOnVacation(random.nextDouble() < 0.1)
                    .isAvailable(random.nextDouble() < 0.9)
                    .birthDate(birthDate)
                    .baseSalary(baseSalary)
                    .build();

            if (Boolean.TRUE.equals(crew.getIsOnVacation())) {
                LocalDate today = LocalDate.now();
                crew.setVacationStartDate(today.minusDays(random.nextInt(7)));
                crew.setVacationEndDate(today.plusDays(random.nextInt(14) + 1));
                crew.setIsAvailable(false);
            }

            crews.add(crew);
        }
        crewRepository.saveAll(crews);
    }

    private Set<PersonalityTraitEnum> generateRandomPersonalities() {
        List<PersonalityTraitEnum> allTraits = Arrays.asList(PersonalityTraitEnum.values());
        Collections.shuffle(allTraits);
        return new HashSet<>(allTraits.subList(0, 3));
    }

    private LocalDate generateRandomBirthDate(CrewRoleEnum role) {
        Random random = new Random();
        int minAge, maxAge;

        switch (role) {
            case ACTOR, STUNT_PERFORMER -> {
                minAge = 5;
                maxAge = (role == CrewRoleEnum.STUNT_PERFORMER) ? 45 : 70;
            }
            case DIRECTOR, WRITER -> {
                minAge = 19;
                maxAge = 75;
            }
            case VFX_ARTIST, SOUND_DESIGNER -> {
                minAge = 21;
                maxAge = 65;
            }
            case CINEMATOGRAPHER -> {
                minAge = 23;  // Requires technical expertise
                maxAge = 70;
            }
            case COMPOSER -> {
                minAge = 20;
                maxAge = 90;
            }
            case MAKEUP_ARTIST, COSTUME_DESIGNER -> {
                minAge = 18;
                maxAge = 70;
            }
            default -> {
                minAge = 18;
                maxAge = 65;
            }
        }
        int age = random.nextInt(maxAge - minAge + 1) + minAge;
        return LocalDate.now().minusYears(age);
    }

}





