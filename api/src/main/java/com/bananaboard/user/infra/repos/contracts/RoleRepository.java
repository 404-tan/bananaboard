package com.bananaboard.user.infra.repos.contracts;

import java.util.Optional;
import java.util.UUID;

import com.bananaboard.user.domain.entities.Role;

public interface RoleRepository {
    boolean existsByName(String name);
    Optional<Role> findById(UUID id);
    Optional<Role> findByName(String name);
    Role save(Role role);
    Role update(Role role);
}
