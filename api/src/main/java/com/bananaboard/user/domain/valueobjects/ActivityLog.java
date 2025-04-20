package com.bananaboard.user.domain.valueobjects;
import java.time.LocalDateTime;



public class ActivityLog {
    private final LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime lastSeenAt;
    public ActivityLog() {
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.lastSeenAt = LocalDateTime.now();
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
    public void updated(){
        this.lastModifiedAt = LocalDateTime.now();
    }
    public void seen(){
        this.lastSeenAt = LocalDateTime.now();
    }
    public boolean isOnline() {
        return lastSeenAt.isAfter(LocalDateTime.now().minusMinutes(5));
    }
}
