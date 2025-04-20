package com.bananaboard.user.infra.persistence.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<AssignedRole> users = new HashSet<>();

    public RoleEntity() {}

    public RoleEntity(UUID id,String name){
        this.id = id;
        this.name = name;
    }
    public RoleEntity(UUID roleId) {
        this.id = roleId;
    }

    public UUID getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
}
