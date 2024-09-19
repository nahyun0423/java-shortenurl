package kr.co.shortenurlservice.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Random;

public class ShortenUrl {
    private static final String CHARACTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int KEY_LENGTH = 5;

    @NotNull
    private String originalUrl;
    @Size(min = KEY_LENGTH, max = KEY_LENGTH)
    private String shortKey;
    private int redirectCount;

    public ShortenUrl(String originalUrl) {
        this.originalUrl = originalUrl;
        this.shortKey = generateKey();
        this.redirectCount = 0;
    }

    public ShortenUrl(String shortKey, String originalUrl) {
        this.shortKey = shortKey;
        this.originalUrl = originalUrl;
    }

    private String generateKey() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return keyBuilder.toString();
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortKey() {
        return shortKey;
    }

    public int getRedirectCount() {
        return redirectCount;
    }
}
