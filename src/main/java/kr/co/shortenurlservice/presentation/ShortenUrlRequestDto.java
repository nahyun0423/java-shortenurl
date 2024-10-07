package kr.co.shortenurlservice.presentation;

import jakarta.validation.constraints.NotNull;

public class ShortenUrlRequestDto {
    @NotNull
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

}
