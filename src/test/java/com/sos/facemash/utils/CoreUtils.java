package com.sos.facemash.utils;

import java.util.Random;
import java.util.UUID;

public class CoreUtils {
    public static final Random RANDOM = new Random();

    public static String randomStringGenerator() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 4);
    }
}
