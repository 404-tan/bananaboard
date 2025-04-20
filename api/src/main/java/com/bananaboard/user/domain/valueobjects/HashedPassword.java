package com.bananaboard.user.domain.valueobjects;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.user.domain.contracts.PasswordHasher;
import com.bananaboard.user.domain.errors.UserDomainError;

import java.util.Objects;

public class HashedPassword {
    private final String value;

    private HashedPassword(String value) {
        this.value = value;
    }
    
    public static HashedPassword fromHashedValue(String hashedValue) {
        if (hashedValue == null || hashedValue.isBlank()) {
            throw new IllegalArgumentException("Hashed password cannot be null or empty");
        }
        return new HashedPassword(hashedValue);
    }

    public static Result<HashedPassword> create(Password password,PasswordHasher hasher) {
        try {
            return Result.success(hasher.hash(password));
        } catch (Exception e) {
            return Result.failure(UserDomainError.HashedPasswordError.HashingFailed);
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "********";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HashedPassword)) return false;
        HashedPassword other = (HashedPassword) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}