package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShortenUrlRepositoryTest {

    private ShortenUrlRepository shortenUrlRepository;

    @BeforeEach
    public void setup() {
        shortenUrlRepository = new ShortenUrlRepository();
    }

    @Test
    public void 단축URL_저장() {
        ShortenUrl shortenUrl = new ShortenUrl("abc12", "http://example.com");
        shortenUrlRepository.save(shortenUrl);

        assertEquals(shortenUrl, shortenUrlRepository.findByKey("abc12"));
    }

    @Test
    public void 단축URL_조회() {
        ShortenUrl shortenUrl = new ShortenUrl("abc12", "http://example.com");
        shortenUrlRepository.save(shortenUrl);

        ShortenUrl result = shortenUrlRepository.findByKey("abc12");
        assertNotNull(result);
        assertEquals(shortenUrl, result);
    }

    @Test
    public void 존재하지않는_단축URL_조회() {
        ShortenUrl result = shortenUrlRepository.findByKey("nonExistKey");
        assertNull(result);
    }
}
