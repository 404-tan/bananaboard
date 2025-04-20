
package com.bananaboard.shared.sharedkernel.utils;
import java.util.*;

import com.bananaboard.shared.sharedkernel.valueobjects.Uuid;

public final class UuidUtils {

    private UuidUtils() {
    }

    public static Set<Uuid> parseUuidSet(Set<String> rawIds) throws IllegalArgumentException {
        Set<Uuid> result = new HashSet<>();
        for (String rawId : rawIds) {
            result.add(new Uuid(rawId));
        };
        return result;
    }
}

