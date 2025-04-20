package com.bananaboard.user.infra.persistence.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private UUID id;

    private String username;
    private String email;
    private String hashedPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileIconEntity profileIcon;

    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime lastSeenAt;
    
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Set<AssignedRole> receivedRoles = new HashSet<>();

    @OneToMany(
        mappedBy = "assignedBy",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private Set<AssignedRole> gaveRoles = new HashSet<>();


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public UUID getProfileIconId() {
        return profileIcon.getId();
    }

    public String getBio() {
        return bio;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    
    public LocalDateTime getLastSeenAt() {
        return lastSeenAt;
    }
    
    public UserEntity(UUID id,
                    String username,
                    String email,
                    String hashedPassword,
                    UUID profileIconId,
                    String bio,
                    LocalDateTime createdAt,
                    LocalDateTime lastModifiedAt,
                    LocalDateTime lastSeenAt,
                    Set<AssignedRole> receivedRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.profileIcon = new ProfileIconEntity(profileIconId);
        this.bio = bio;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.lastSeenAt = lastSeenAt;
        this.receivedRoles = receivedRoles;
        this.gaveRoles = new HashSet<>();
    }

    public UserEntity(UUID userId) {
        this.id = userId;
    }
    public UserEntity() {}
    
    public UUID getId(){
        return id;
    }
    public Set<AssignedRole> getReceivedRoles() {
        return receivedRoles;
    }

}
