package com.bananaboard.user.infra.repos.contracts;
import java.util.Optional;
import java.util.UUID;

import com.bananaboard.user.domain.entities.User;

public interface UserRepository{
    boolean existsById(UUID id);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    User save(User user);
    User update(User user);    
}
