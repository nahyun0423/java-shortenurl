package kr.co.shortenurlservice.domain;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class ShortenUrl {
    private static final Pattern URL_PATTERN = Pattern.compile("^(http|https)://.*$");

    @NotNull
    private String originalUrl;
    private String shortKey;
    private int redirectCount;

    public ShortenUrl(String originalUrl, ShortKeyGenerator shortKeyGenerator) {
        validateUrl(originalUrl);

        this.originalUrl = originalUrl;
        this.shortKey = shortKeyGenerator.generateKey();
        this.redirectCount = 0;
    }

    public ShortenUrl(String shortKey, String originalUrl) {
        validateUrl(originalUrl);

        this.shortKey = shortKey;
        this.originalUrl = originalUrl;
        this.redirectCount = getRedirectCount();
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
