package kr.co.shortenurlservice.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Random;
import java.util.regex.Pattern;

@Getter
public class ShortenUrl {
    private static final String CHARACTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int KEY_LENGTH = 5;
    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*$");


    @NotNull
    private String originalUrl;

    @Getter
    @Size(min = KEY_LENGTH, max = KEY_LENGTH)
    private String shortKey;
    private int redirectCount;

    public ShortenUrl(String originalUrl) {
        validateUrl(originalUrl);

        this.originalUrl = originalUrl;
        this.shortKey = generateKey();
        this.redirectCount = 0;
    }

    public ShortenUrl(String shortKey, String originalUrl) {
        validateUrl(originalUrl);

        this.shortKey = shortKey;
        this.originalUrl = originalUrl;
        this.redirectCount = getRedirectCount();
    }

    private String generateKey() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder(KEY_LENGTH);
        for (int i = 0; i < KEY_LENGTH; i++) {
            keyBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return keyBuilder.toString();
    }

    private void validateUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("원본 URL이 비어있습니다.");
        }
        if (!URL_PATTERN.matcher(url).matches()) {
            throw new IllegalArgumentException("URL 형식에 맞지 않습니다.");
        }
    }

    public void increaseRedirectCount() {
        this.redirectCount++;
    }

}
