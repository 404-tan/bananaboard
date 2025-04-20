package com.bananaboard.user.infra.persistence.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ProfileIconEntity {
    @Id
    private UUID id;
    private String name;
    private String iconPath;
    private String mimeType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Set<UserEntity> users = new HashSet<>();

    public ProfileIconEntity(UUID profileIconId,String name, String iconPath, String mimeType) {
        this.id = profileIconId;
        this.name = name;
        this.iconPath = iconPath;
        this.mimeType = mimeType;
        this.users = new HashSet<>(); 
    }
    public ProfileIconEntity(UUID profileIconId) {
        this.id = profileIconId;
    }
    public ProfileIconEntity() {}

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getIconPath() {
        return iconPath;
    }
    public String getMimeType() {
        return mimeType;
    }

}
