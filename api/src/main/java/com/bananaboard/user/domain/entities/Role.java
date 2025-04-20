package com.bananaboard.user.domain.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;
import com.bananaboard.user.domain.valueobjects.RoleName;

public class Role {
    private Uuid id;
    private RoleName name;
    private Set<Uuid> usersIds;
    
    public Role(RoleName name) {
        this.id = new Uuid();
        this.usersIds = Set.of();
        this.name = name;
    }


    public UUID getId() {                                                                     
        return id.getValue();
    }

    public List<UUID> getUsersIds() {
        return this.usersIds.stream()
                        .map(uuid -> uuid.getValue())
                        .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.getValue();
    }

}
