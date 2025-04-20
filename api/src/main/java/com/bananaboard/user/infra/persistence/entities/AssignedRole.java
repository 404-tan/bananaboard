package com.bananaboard.user.infra.persistence.entities;
import java.time.LocalDateTime;
import java.util.UUID;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "assigned_role")
public class AssignedRole {

    @EmbeddedId
    private UserRoleId id = new UserRoleId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId")
    private RoleEntity role;

    private LocalDateTime assignedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    private UserEntity assignedBy;

    public AssignedRole() {}

    public AssignedRole(UUID userId, UUID roleId, UUID assignedById) {
        this.user = new UserEntity(userId);
        this.role = new RoleEntity(roleId);
        this.assignedBy = new UserEntity(assignedById);
        this.id = new UserRoleId(userId, roleId);
        this.assignedAt = LocalDateTime.now();
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
    public UUID getRoleId() {
        return role.getId();
    }

}