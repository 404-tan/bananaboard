package com.bananaboard.user.domain.entities;

import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.bananaboard.sharedkernel.valueobjects.Uuid;

public class Role implements GrantedAuthority{
    private Uuid id;
    private String name;
    private Set<User> users;
    
    public Role(String name) {
        this.id = new Uuid();
        this.users = Set.of();
        this.name = "ROLE_" + name.toUpperCase();
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public String getId() {                                                                     
        return id.toString();
    }

    public Set<User> getUsers() {
        return users;
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
        return name;
    }

}
