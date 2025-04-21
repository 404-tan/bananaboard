package com.bananaboard.user.infra.helpers.contracts;

import com.bananaboard.user.domain.entities.Role;
import com.bananaboard.user.infra.persistence.entities.RoleEntity;

public interface RoleMapper {
    Role toDomain(RoleEntity roleEntity);
    RoleEntity toEntity(Role role);
}
