package kr.co.shortenurlservice;

import kr.co.shortenurlservice.application.ShortenUrlService;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShortenurlserviceApplicationTests {

	private ShortenUrlRepository shortenUrlRepository;
	private ShortenUrlService shortenUrlService;

	@BeforeEach
	public void setup() {
		shortenUrlRepository = new ShortenUrlRepository();
		shortenUrlService = new ShortenUrlService(shortenUrlRepository);
	}

	@Test
	void testCreateShortenUrl() {
		String originalUrl = "https://example.com";
		String shortenKey = shortenUrlService.createShortUrl(originalUrl).toString();

	}

}
