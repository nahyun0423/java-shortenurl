package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortKeyGenerator;
import kr.co.shortenurlservice.domain.ShortenUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ShortenUrlTest {
    @Mock
    private ShortKeyGenerator shortKeyGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void 생성자에서_단축키_생성() {
        String originalUrl = "http://example.com";
        String generatedKey = "abc12";
        when(shortKeyGenerator.generateKey()).thenReturn(generatedKey);
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortKeyGenerator);

        assertEquals(originalUrl, shortenUrl.getOriginalUrl());
        assertEquals(generatedKey, shortenUrl.getShortKey());
        assertEquals(0, shortenUrl.getRedirectCount());
    }

    @Test
    public void 생성자에서_예외_잘못된_URL() {
        String invalidUrl = "invalid-url";
        assertThrows(IllegalArgumentException.class, () -> new ShortenUrl(invalidUrl, shortKeyGenerator));
    }

    @Test
    public void 리다이렉트_카운트_증가() {
        ShortenUrl shortenUrl = new ShortenUrl("http://example.com", shortKeyGenerator);
        shortenUrl.increaseRedirectCount();

        assertEquals(1, shortenUrl.getRedirectCount());
    }
}
