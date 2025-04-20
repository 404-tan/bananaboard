package com.bananaboard.user.infra.helpers.impl;

import java.util.stream.Collectors;
import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.entities.User;
import com.bananaboard.user.domain.valueobjects.Biography;
import com.bananaboard.user.domain.valueobjects.Email;
import com.bananaboard.user.domain.valueobjects.HashedPassword;
import com.bananaboard.user.domain.valueobjects.Username;
import com.bananaboard.user.infra.helpers.contracts.UserMapper;
import com.bananaboard.user.infra.persistence.entities.AssignedRole;

import com.bananaboard.user.infra.persistence.entities.UserEntity;

public class UserMapperImpl implements UserMapper {
    
    /* NEED TO REFACTOR */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        return new User(
            Username.create(entity.getUsername()).getValue(),
            Email.create(entity.getEmail()).getValue(),
            HashedPassword.fromHashedValue(entity.getHashedPassword()),
            new Uuid(entity.getProfileIconId().toString()),
            Biography.create(entity.getBio()).getValue(),
            entity.getReceivedRoles().stream()
                .map(role -> new Uuid(role.getRoleId().toString()))
                .collect(Collectors.toSet())
        );
    }
    /* NEED TO REFACTOR */
    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
   

        UserEntity entity = new UserEntity( 
            domain.getId(),
            domain.getUsername(),
            domain.getEmail(),
            domain.getPassword(),
            domain.getProfileIconId(),
            domain.getBio(),
            domain.getCreatedAt(),
            domain.getLastModifiedAt(),
            domain.getLastSeenAt(),
            domain.getUserRoles().stream()
                .map(roleId -> new AssignedRole(
                    domain.getId(),
                    roleId,
                    null 
                ))
                .collect(Collectors.toSet())
        );

        return entity;
    }
}
