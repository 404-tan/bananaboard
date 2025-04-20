package com.bananaboard.shared.sharedkernel.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.bananaboard.shared.sharedkernel.validation.Result;
import com.bananaboard.shared.sharedkernel.validation.Error;

public abstract class BaseBuilder<T> {
    protected final List<Error> errors = new ArrayList<>();

    protected <R> void applyIfSuccess(Result<R> result, Consumer<R> setter) {
        if (result.isSuccess()) setter.accept(result.getValue());
        else errors.addAll(result.getErrors());
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<Error> getErrors() {
        return errors;
    }

    public abstract Result<T> build();
}
