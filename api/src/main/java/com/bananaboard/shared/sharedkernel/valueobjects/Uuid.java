package com.bananaboard.shared.sharedkernel.valueobjects;

import java.util.UUID;

public class Uuid {
    private UUID value;
    public Uuid () {
        this.value = UUID.randomUUID();
    }
    public Uuid(UUID uuid) {
        this.value = uuid;
    }
    public UUID getValue() {
        return this.value;
    }
}
