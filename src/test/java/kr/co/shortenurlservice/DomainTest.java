package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DomainTest {

    private ShortenUrl shortenUrl;

    @BeforeEach
    void setUp() {
        shortenUrl =  new ShortenUrl("https://example.com");
    }

    @Test
    void testGenerateKey() {
        String key = shortenUrl.getShortKey();

        Assertions.assertNotNull(key);
        assertEquals(5, key.length());
    }

    @Test
    void testIncreaseRedirectCount() {
        assertEquals(0, shortenUrl.getRedirectCount());

        shortenUrl.increaseRedirectCount();
        assertEquals(1, shortenUrl.getRedirectCount());
    }
}
