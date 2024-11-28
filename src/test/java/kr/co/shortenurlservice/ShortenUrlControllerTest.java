package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import kr.co.shortenurlservice.presentation.ShortenUrlRequestDto;
import kr.co.shortenurlservice.presentation.ShortenUrlController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ShortenUrlControllerTest {
    @Mock
    private ShortenUrlService shortenUrlService;

    @InjectMocks
    private ShortenUrlController shortenUrlController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void 단축URL_생성_성공() {
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto("http://example.com", "abc12", 0);
        ShortenUrlRequestDto requestDto = new ShortenUrlRequestDto();

        when(shortenUrlService.createShortUrl(requestDto.getOriginalUrl())).thenReturn(shortenUrlDto);
        ResponseEntity<ShortenUrlDto> response = shortenUrlController.createShortenUrl(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("abc12", response.getBody().getShortKey());
    }

    @Test
    public void 단축URL_조회_성공() {
        ShortenUrlDto shortenUrlDto = new ShortenUrlDto("http://example.com", "abc12", 0);

        when(shortenUrlService.findByKey("abc12")).thenReturn(shortenUrlDto);
        ShortenUrlDto result = shortenUrlController.findShortenUrl("abc12");

        assertNotNull(result);
        assertEquals("abc12", result.getShortKey());
    }

    @Test
    public void 단축URL_리다이렉트_성공() {
        when(shortenUrlService.redirectUrl("abc12")).thenReturn("http://example.com");
        ResponseEntity<?> response = shortenUrlController.redirectUrl("abc12");

        assertEquals(302, response.getStatusCodeValue());
        assertTrue(response.getHeaders().containsKey("Location"));
        assertEquals("http://example.com", response.getHeaders().get("Location").get(0));
    }

    @Test
    public void 단축URL_리다이렉트_실패() {
        when(shortenUrlService.redirectUrl("nonExistKey")).thenReturn(null);
        ResponseEntity<?> response = shortenUrlController.redirectUrl("nonExistKey");

        assertEquals(404, response.getStatusCodeValue());
    }
}
