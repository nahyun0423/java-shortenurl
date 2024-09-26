package kr.co.shortenurlservice;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ShortenurlserviceApplicationTests {

	private ShortenUrlRepository shortenUrlRepository;

	@BeforeEach
	public void setup() {
		shortenUrlRepository = new ShortenUrlRepository();
	}

	@Test
	void testSaveAndFindByKey() {
		String originalUrl = "https://example.com";
		ShortenUrl shortenUrl = new ShortenUrl(originalUrl);

		shortenUrlRepository.save(shortenUrl);

		ShortenUrl url = shortenUrlRepository.findByKey(shortenUrl.getShortKey());
		assertEquals(originalUrl, url.getOriginalUrl());
		assertEquals(shortenUrl.getShortKey(), url.getShortKey());
	}

	@Test
	void testGenerateKey() {
		ShortenUrl shortenUrl = new ShortenUrl("https://example.com");
		String key = shortenUrl.getShortKey();

		Assertions.assertNotNull(key);
		assertEquals(5, key.length());
	}

	@Test
	void testIncreaseRedirectCount() {
		ShortenUrl shortenUrl = new ShortenUrl("https://example.com");
		assertEquals(0, shortenUrl.getRedirectCount());

		shortenUrl.increaseRedirectCount();
		assertEquals(1, shortenUrl.getRedirectCount());
	}
}
