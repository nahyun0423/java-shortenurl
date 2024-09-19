package kr.co.shortenurlservice.presentation;

import jakarta.validation.constraints.NotNull;
import kr.co.shortenurlservice.domain.ShortenUrl;

public class ShortenUrlDto {
    @NotNull
    private String originalUrl;
    @NotNull
    private String shortKey;

    public ShortenUrlDto(String originalUrl, String shortKey) {
        this.originalUrl = originalUrl;
        this.shortKey = shortKey;
    }

    public static ShortenUrlDto toDto(ShortenUrl shortenUrl) {
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto(
                shortenUrl.getOriginalUrl(),
                shortenUrl.getShortKey()
        ) ;
        return shortenUrlDto;
    }

}
