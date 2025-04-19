package com.bananaboard.user.domain.valueobjects;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.user.domain.errors.UserDomainError;

import java.util.Objects;

public class HashedPassword {
    private final String value;

    private HashedPassword(String value) {
        this.value = value;
    }

    public static Result<HashedPassword> create(String value) {
        if (value.trim().isEmpty()) return Result.failure(UserDomainError.HashedPasswordError.EmptyHash);
    
        return Result.success(new HashedPassword(value));
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "********"; // nunca expõe o hash real
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