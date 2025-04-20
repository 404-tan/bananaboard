package com.bananaboard.shared.sharedkernel.exceptions;

public class InvalidUuidException extends RuntimeException {
    private final String invalidId;

    public InvalidUuidException(String invalidId) {
        super("Invalid UUID: " + invalidId);
        this.invalidId = invalidId;
    }

    public String getInvalidId() {
        return invalidId;
    }
}
