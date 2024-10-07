package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.domain.RandomShortKeyGenerator;
import kr.co.shortenurlservice.domain.ShortKeyGenerator;
import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.MapShortenShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class ShortenUrlServiceTest {

    @Mock
    private ShortenUrlService shortenUrlService;

    @Mock
    private MapShortenShortenUrlRepository mapShortenUrlRepository;

    @Mock
    private ShortKeyGenerator shortKeyGenerator;

    @BeforeEach
    public void setUp() {
        mapShortenUrlRepository = new MapShortenShortenUrlRepository();
        shortKeyGenerator = new RandomShortKeyGenerator();
        shortenUrlService = new ShortenUrlService(mapShortenUrlRepository);
    }

    @Test
    public void 단축URL_만들기_성공() {
        String url = "http://example.com";
        String generatedKey = "abc12";
        Mockito.when(shortKeyGenerator.generateKey()).thenReturn(generatedKey);
        ShortenUrlDto shortenUrlDto = shortenUrlService.createShortUrl(url);

        assertNotNull(shortenUrlDto);
        assertEquals(url, shortenUrlDto.getOriginalUrl());
        assertEquals(generatedKey, shortenUrlDto.getShortKey());
    }

    @Test
    public void 단축URL_만들기_실패_URL이_null일때() {
        String url = null;
        assertThrows(ResponseStatusException.class, () -> shortenUrlService.createShortUrl(url));
    }

    @Test
    public void 단축URL_조회_성공() {
        String shortKey = "abc12";
        ShortenUrl shortenUrl = new ShortenUrl("http://example.com", shortKeyGenerator);
        Mockito.when(mapShortenUrlRepository.findByKey(shortKey)).thenReturn(shortenUrl);
        ShortenUrlDto shortenUrlDto = shortenUrlService.findByKey(shortKey);

        assertNotNull(shortenUrlDto);
        assertEquals(shortKey, shortenUrlDto.getShortKey());
        Mockito.verify(mapShortenUrlRepository, Mockito.times(1)).findByKey(shortKey);
    }

    @Test
    public void 단축URL_조회_실패_존재하지않는_key() {
        String shortKey = "nonExistKey";
        Mockito.when(mapShortenUrlRepository.findByKey(shortKey)).thenReturn(null);
        assertThrows(ResponseStatusException.class, () -> shortenUrlService.findByKey(shortKey));
    }

    @Test
    public void 리다이렉트_URL_성공() {
        String shortKey = "abc12";
        String originalUrl = "http://example.com";
        ShortenUrl shortenUrl = new ShortenUrl(originalUrl, shortKeyGenerator);
        Mockito.when(mapShortenUrlRepository.findByKey(shortKey)).thenReturn(shortenUrl);
        String result = shortenUrlService.redirectUrl(shortKey);

        assertEquals(originalUrl, result);
        Mockito.verify(mapShortenUrlRepository, Mockito.times(1)).save(shortenUrl);
    }

    @Test
    public void 리다이렉트_URL_실패_존재하지않는_key() {
        String shortKey = "nonExistKey";
        Mockito.when(mapShortenUrlRepository.findByKey(shortKey)).thenReturn(null);
        String result = shortenUrlService.redirectUrl(shortKey);

        assertNull(result);
    }
}
