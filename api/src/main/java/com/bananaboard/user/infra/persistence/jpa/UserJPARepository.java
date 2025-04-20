package com.bananaboard.user.infra.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bananaboard.user.infra.persistence.entities.UserEntity;

public interface UserJPARepository extends JpaRepository<UserEntity, UUID> {
    boolean existsById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);

}
