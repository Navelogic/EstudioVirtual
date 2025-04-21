package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Production.Serie.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
