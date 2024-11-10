package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.infrastructure.jpa.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("prod")
public class ShortenUrlTest {

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    private ShortenUrlService shortenUrlService;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        System.out.println(System.getProperty("spring.profiles.active"));
        shortenUrlRepository.deleteAll();
    }

    @Test
    public void 단축URL_생성_테스트() {
        String originalUrl = "http://example.com";
        ShortenUrlDto requestDto = new ShortenUrlDto(originalUrl, null, 0);
        ResponseEntity<ShortenUrlDto> response = testRestTemplate.postForEntity("http://localhost:" + port + "/shortenUrl", requestDto, ShortenUrlDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ShortenUrlDto shortenUrlDto = response.getBody();
        assertNotNull(shortenUrlDto);
        assertEquals(originalUrl, shortenUrlDto.getOriginalUrl());
        assertNotNull(shortenUrlDto.getShortKey());
        assertEquals(0, shortenUrlDto.getRedirectCount());
    }

    @Test
    public void 단축URL_조회_테스트() {
        String originalUrl = "http://example.com";
        ShortenUrlDto createdUrlDto = shortenUrlService.createShortUrl(originalUrl);
        ResponseEntity<ShortenUrlDto> response = testRestTemplate.getForEntity("http://localhost:" + port + "/check/" + createdUrlDto.getShortKey(), ShortenUrlDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        ShortenUrlDto foundUrlDto = response.getBody();
        assertNotNull(foundUrlDto);
        assertEquals(originalUrl, foundUrlDto.getOriginalUrl());
    }

    @Test
    public void 모든_단축URL_조회_테스트() {
        shortenUrlService.createShortUrl("http://example1.com");
        shortenUrlService.createShortUrl("http://example2.com");
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:" + port + "/urls", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<?> urls = response.getBody();
        assertNotNull(urls);
        assertEquals(2, urls.size());
    }

    @Test
    public void 리다이렉트_테스트() {
        String originalUrl = "https://naver.com/";
        ShortenUrlDto createdUrlDto = shortenUrlService.createShortUrl(originalUrl);
        ResponseEntity<Void> response = testRestTemplate.getForEntity("http://localhost:" + port + "/" + createdUrlDto.getShortKey(), Void.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation());
        assertEquals(originalUrl, response.getHeaders().getLocation().toString());
    }

    @Test
    public void 잘못된_ShortKey_조회시_예외() {
        String invalidShortKey = "invalidKey";
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:" + port + "/check/" + invalidShortKey, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
