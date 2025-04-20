package com.bananaboard.user.infra.repos.implementations;

import java.util.Optional;
import java.util.UUID;

import com.bananaboard.user.domain.entities.User;
import com.bananaboard.user.infra.helpers.contracts.UserMapper;
import com.bananaboard.user.infra.persistence.jpa.UserJPARepository;
import com.bananaboard.user.infra.repos.contracts.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository jpaRepository;
    private final UserMapper userMapper;
    public UserRepositoryImpl(UserJPARepository jpaRepository, UserMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.userMapper = userMapper;
    }
    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<User> user = jpaRepository.findById(id).map(userMapper::toDomain);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = jpaRepository.findByEmail(email).map(userMapper::toDomain);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user = jpaRepository.findByUsername(username).map(userMapper::toDomain);
        return user;
    }

    @Override
    public User save(User user) {
        jpaRepository.save(userMapper.toEntity(user));
        return user;
    }

    @Override
    public User update(User user) {
        jpaRepository.save(userMapper.toEntity(user));
        return user;
    }

}
