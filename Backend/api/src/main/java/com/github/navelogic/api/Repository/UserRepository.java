package com.github.navelogic.api.Repository;

import com.github.navelogic.api.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel>findByEmail(String email);
    Optional<UserModel>findByUsername(String username);
}
