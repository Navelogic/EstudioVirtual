package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Production.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
