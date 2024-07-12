package com.learn.spring.utils;

import java.util.Optional;

public class Utils {
    public static <T> void throwIfPresent(Optional<T> op, String message) {
        if (op.isPresent()) {
            throw new RuntimeException(message);
        }
    }
}
