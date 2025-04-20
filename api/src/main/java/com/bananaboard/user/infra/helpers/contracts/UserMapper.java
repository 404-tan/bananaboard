package com.bananaboard.user.infra.helpers.contracts;

import com.bananaboard.user.domain.entities.User;
import com.bananaboard.user.infra.persistence.entities.UserEntity;

public interface UserMapper {
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);
}
