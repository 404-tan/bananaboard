package com.bananaboard.sharedkernel.validation;

import java.util.Objects;

public final class Error {
    public static final Error None = new Error("", "");
    public static final Error NullValue = new Error("Error.NullValue", "The specified result value is null.");

    private final String code;
    private final String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String asString(Error error) {
        return error == null ? null : error.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Error)) return false;
        Error error = (Error) o;
        return Objects.equals(code, error.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}