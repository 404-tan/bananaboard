package com.bananaboard.shared.sharedkernel.validation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Result<T> {
    private final T value;
    private final List<Error> errors;
    private final boolean success;

    private Result(T value, List<Error> errors, boolean success) {
        this.value = value;
        this.errors = errors;
        this.success = success;
    }
    
    public static <T> Result<T> success(T value) {
        Objects.requireNonNull(value, "Success value cannot be null");
        return new Result<>(value, Collections.emptyList(), true);
    }
    public static Result<Void> success() {
        return new Result<>(null, Collections.emptyList(), true);
    }

    public static <T> Result<T> failure(Error error) {
        Objects.requireNonNull(error, "Error cannot be null");
        return new Result<>(null, List.of(error), false);
    }

    public static <T> Result<T> failure(List<Error> errors) {
        Objects.requireNonNull(errors, "Errors list cannot be null");
        if (errors.isEmpty()) throw new IllegalArgumentException("Errors list must not be empty");
        return new Result<>(null, List.copyOf(errors), false);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public T getValue() {
        if (!success) {
            throw new IllegalStateException("Cannot get value from a failed result");
        }
        return value;
    }

    public List<Error> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return success ? "Success(" + value + ")" : "Failure(" + errors + ")";
    }
}