package kr.co.shortenurlservice.presentation;

import jakarta.validation.constraints.NotBlank;

public class ShortenUrlRequestDto {
    @NotBlank(message = "URL 입력이 잘못되었습니다.")
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

}
