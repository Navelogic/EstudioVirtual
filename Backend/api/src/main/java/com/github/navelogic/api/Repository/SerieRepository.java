package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Production.Serie.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
}
