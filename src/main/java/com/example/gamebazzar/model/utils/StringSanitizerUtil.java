package com.example.gamebazzar.model.utils;

import java.util.Random;

public class StringSanitizerUtil {

    private StringSanitizerUtil() {
        // Private constructor to prevent instantiation
    }


    public static String sanitizeQuotes(String input) {
        if (input != null) {
            return input.replaceAll("^\"|\"$", "");
        }
        return input;
    }


    public static String trimWhitespace(String input) {
        if (input != null) {
            return input.trim();
        }
        return input;
    }


    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }


    public static String normalizeString(String input) {
        if (input != null) {
            return sanitizeQuotes(trimWhitespace(input));
        }
        return input;
    }

    public static String generateRandomGameCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder gameCode = new StringBuilder();

        for (int i = 0; i < 10; i++) {  // Generate a 10-character code
            int randomIndex = random.nextInt(characters.length());
            gameCode.append(characters.charAt(randomIndex));
        }

        return gameCode.toString();
    }


    public static String ensureNotNull(String input) {
        return input == null ? "" : input;
    }
}
