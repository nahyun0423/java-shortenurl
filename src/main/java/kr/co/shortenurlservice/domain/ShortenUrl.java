package kr.co.shortenurlservice.domain;

import java.util.Random;

public class ShortenUrl {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int KEY_LENGTH = 5;
    private String originalUrl;
    private String shortKey;


    public ShortenUrl(String originalUrl) {
        this.originalUrl = originalUrl;
        this.shortKey = generateKey();
    }

    private String generateKey() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return keyBuilder.toString();
    }
}
