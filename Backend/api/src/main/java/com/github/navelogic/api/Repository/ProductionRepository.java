package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Production.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
}
