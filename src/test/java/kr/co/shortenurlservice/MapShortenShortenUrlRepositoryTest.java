package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.MapShortenShortenUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapShortenShortenUrlRepositoryTest {

    private MapShortenShortenUrlRepository mapShortenUrlRepository;

    @BeforeEach
    public void setup() {
        mapShortenUrlRepository = new MapShortenShortenUrlRepository();
    }

    @Test
    public void 단축URL_저장() {
        ShortenUrl shortenUrl = new ShortenUrl("abc12", "http://example.com");
        mapShortenUrlRepository.save(shortenUrl);

        assertEquals(shortenUrl, mapShortenUrlRepository.findByKey("abc12"));
    }

    @Test
    public void 단축URL_조회() {
        ShortenUrl shortenUrl = new ShortenUrl("abc12", "http://example.com");
        mapShortenUrlRepository.save(shortenUrl);

        ShortenUrl result = mapShortenUrlRepository.findByKey("abc12");
        assertNotNull(result);
        assertEquals(shortenUrl, result);
    }

    @Test
    public void 존재하지않는_단축URL_조회() {
        ShortenUrl result = mapShortenUrlRepository.findByKey("nonExistKey");
        assertNull(result);
    }
}
