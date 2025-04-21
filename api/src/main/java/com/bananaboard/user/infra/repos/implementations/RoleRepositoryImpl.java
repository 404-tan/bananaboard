package com.bananaboard.user.infra.repos.implementations;

import java.util.Optional;
import java.util.UUID;

import com.bananaboard.user.domain.entities.Role;
import com.bananaboard.user.infra.helpers.contracts.RoleMapper;
import com.bananaboard.user.infra.persistence.jpa.RoleJPARepository;
import com.bananaboard.user.infra.repos.contracts.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository {
    private final RoleJPARepository jpaRepository;
    private final RoleMapper roleMapper;
    public RoleRepositoryImpl(RoleJPARepository jpaRepository, RoleMapper roleMapper) {
        this.jpaRepository = jpaRepository;
        this.roleMapper = roleMapper;
    }
    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }
    @Override
    public Optional<Role> findById(UUID id) {
        Optional<Role> role = jpaRepository.findById(id).map(roleMapper::toDomain);
        return role;
    }
    @Override
    public Optional<Role> findByName(String name) {
        Optional<Role> role = jpaRepository.findByName(name).map(roleMapper::toDomain);
        return role;
    }
    @Override
    public Role save(Role role) {
        jpaRepository.save(roleMapper.toEntity(role));
        return role;
    }
    @Override
    public Role update(Role role) {
        jpaRepository.save(roleMapper.toEntity(role));
        return role;
    }
}
