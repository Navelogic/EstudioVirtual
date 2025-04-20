package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Production.Serie.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
