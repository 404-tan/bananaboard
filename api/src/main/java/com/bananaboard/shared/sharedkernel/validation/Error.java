package com.bananaboard.shared.sharedkernel.validation;

import java.text.MessageFormat;
import java.util.Objects;

public final class Error {
    public static final Error None = new Error("","", "");
    public static final Error NullValue = new Error("","Error.NullValue", "The specified result value is null.");

    private final String code;
    private final String message;
    private final String context;
    public Error(String context,String code, String message) {
        this.context = context;
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public String getContext(){
        return context;
    }

    public String getMessage() {
        return message;
    }

    public static String asString(Error error) {
        return error == null ? null : error.toString();
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
        return MessageFormat.format("{0}.{1}",this.context,this.code);
    }
}