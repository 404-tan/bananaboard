
package com.bananaboard.sharedkernel.utils;

import com.bananaboard.sharedkernel.validation.Result;
import com.bananaboard.sharedkernel.validation.Error;
import com.bananaboard.sharedkernel.valueobjects.Uuid;

import java.util.*;

public final class UuidUtils {

    private UuidUtils() {
        // utilitário estático, construtor privado
    }

    public static Result<Set<Uuid>> parseUuidSet(Set<String> rawIds, String context) {
        Set<Uuid> result = new HashSet<>();
        List<Error> errors = new ArrayList<Error>();
        for (String rawId : rawIds) {
            if (rawId == null || rawId.isBlank()) {
                errors.add(new Error(
                    context + ".Empty",
                    context + " ID cannot be null or empty."
                ));
                continue;
            }

            try {
                result.add(new Uuid(rawId));
            } catch (IllegalArgumentException ex) {
                errors.add(new Error(
                    context + ".Invalid",
                    "Invalid " + context.toLowerCase() + " ID: " + rawId
                ));
            }
        }
        if(!errors.isEmpty()) Result.failure(errors);
        return Result.success(result);
    }
}

