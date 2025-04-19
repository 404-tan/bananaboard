package com.bananaboard.sharedkernel.valueobjects;

import java.util.UUID;

public class Uuid {
    private UUID value;
    public Uuid () {
        this.value = UUID.randomUUID();
    }
    public Uuid(String uuid) {
        this.value = UUID.fromString(uuid);
    }
    public String getValue() {
        return value.toString();
    }
}
