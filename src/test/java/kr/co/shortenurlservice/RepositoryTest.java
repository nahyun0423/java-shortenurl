package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RepositoryTest {

    private ShortenUrlRepository shortenUrlRepository;

    @BeforeEach
    public void setup() {
        shortenUrlRepository = new ShortenUrlRepository();
    }

    @Test
    void testSaveAndFindByKey() {
        String originalUrl = "https://example.com";
        ShortenUrl shortenUrl = new ShortenUrl("https://example.com");

        shortenUrlRepository.save(shortenUrl);

        ShortenUrl url = shortenUrlRepository.findByKey(shortenUrl.getShortKey());
        assertEquals(originalUrl, url.getOriginalUrl());
        assertEquals(shortenUrl.getShortKey(), url.getShortKey());
    }
}
