package com.bananaboard.user.infra.persistence.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bananaboard.user.infra.persistence.entities.RoleEntity;

public interface RoleJPARepository extends JpaRepository<RoleEntity,UUID> {
    boolean existsById(UUID id);
    boolean existsByName(String name);
    Optional<RoleEntity> findById(UUID id);
    Optional<RoleEntity> findByName(String name);
}
