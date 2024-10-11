package kr.co.shortenurlservice.presentation;

import jakarta.validation.constraints.NotNull;
import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlDto {
    @NotNull
    private String originalUrl;
    @NotNull
    private String shortKey;
    @NotNull
    private int redirectCount;

    public ShortenUrlDto(String originalUrl, String shortKey, int redirectCount) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
        this.redirectCount = redirectCount;
    }

    public static ShortenUrlDto toDto(ShortenUrl shortenUrl) {
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(
                shortenUrl.getOriginalUrl(),
                shortenUrl.getShortKey(),
                shortenUrl.getRedirectCount()
        ) ;
        return shortenUrlDto;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortKey() {
        return shortKey;
    }
}
