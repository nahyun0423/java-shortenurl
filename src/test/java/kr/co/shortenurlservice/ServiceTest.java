package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ServiceTest {
    private ShortenUrlRepository shortenUrlRepository;
    private ShortenUrlService shortenUrlService;

    @Test
    void testCreateShortUrlInvalidUrl() {
        String invalidUrl = "";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            shortenUrlService.createShortUrl(invalidUrl);
        });
        assertEquals("URL 입력이 잘못되었습니다", exception.getReason());
    }


    @Test
    void testFindByKeySuccess() {
        String shortKey = "abcde";
        ShortenUrl shortenUrl = new ShortenUrl(shortKey, "http://www.example.com");
        when(shortenUrlRepository.findByKey(shortKey)).thenReturn(shortenUrl);
        ShortenUrlDto result = shortenUrlService.findByKey(shortKey);

        assertNotNull(result);
        assertEquals(shortKey, result.getShortKey());
    }

    @Test
    void testRedirectUrlNotFound() {
        String shortKey = "invalidKey";
        when(shortenUrlRepository.findByKey(shortKey)).thenReturn(null);

        String result = shortenUrlService.redirectUrl(shortKey);

        assertNull(result);
    }
}
