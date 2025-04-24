package com.github.navelogic.api.Service.AI;

import com.github.navelogic.api.Model.Studio;
import com.github.navelogic.api.Repository.StudioRepository;
import com.github.navelogic.api.Service.Crew.CrewService;
import com.github.navelogic.api.Util.NameGenerationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudioAIService {

    private final StudioRepository studioRepository;
    private final NameGenerationUtil nameGenerationUtil;
    private final CrewService crewService;
    private final Random random = new Random();

    @Transactional
    @Scheduled(fixedRate = 14400)
    public void scheduleCreateAiStudios() {
        if (random.nextDouble() < 0.05) {
            String studioName = nameGenerationUtil.generateStudioName();
            String description = nameGenerationUtil.generateStudioDescription(studioName);

            crewService.generateRandomCrews();
            Studio studio = new Studio();
            studio.setName(studioName);
            studio.setDescription(description);
            studio.setIsAiControlled(true);

            studioRepository.save(studio);
        }
    }
}
