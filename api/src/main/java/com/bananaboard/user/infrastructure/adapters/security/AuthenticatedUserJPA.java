package com.bananaboard.user.infrastructure.adapters.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bananaboard.user.domain.entities.User;

public class AuthenticatedUserJPA implements UserDetails{
    private final User user;
    private final Set<String> rolesNames;
    public AuthenticatedUserJPA(User user,Set<String> rolesNames) {
        this.user = user;
        this.rolesNames = rolesNames;
    }

    public User getDomainUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesNames.stream()
            .map(role -> (GrantedAuthority) () -> role)
            .collect(Collectors.toSet());
    }
    @Override
    public String getPassword() {
        return user.getPassword(); 
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
