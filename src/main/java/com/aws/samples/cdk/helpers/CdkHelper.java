package com.aws.samples.cdk.helpers;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class CdkHelper {
    public static final String NO_SEPARATOR = "";
    private static Optional<String> stackName = Optional.empty();
    private static Optional<Random> random = Optional.empty();
    private static Optional<Map<String, String>> arguments = Optional.ofNullable(System.getenv());

    public static void setStackName(String stackName) {
        CdkHelper.stackName = Optional.of(stackName);
    }

    public static Optional<Map<String, String>> getArguments() {
        return arguments;
    }

    public static String getRandomId() {
        return "id" + nextRandomLong();
    }

    private static Long nextRandomLong() {
        if (!random.isPresent()) {
            stackName.orElseThrow(() -> new RuntimeException("Stack name must be present"));

            random = Optional.of(new Random(UUID.nameUUIDFromBytes(CdkHelper.stackName.get().getBytes()).getLeastSignificantBits()));
        }

        return random.get().nextLong();
    }
}