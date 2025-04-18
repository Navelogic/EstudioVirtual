package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.Studio;
import com.github.navelogic.api.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
    Optional<UserModel> findByOwnerId(UUID userId);
}
