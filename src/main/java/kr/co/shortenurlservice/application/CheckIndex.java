package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.infrastructure.jpa.DatabaseShortenUrlRepository;
import kr.co.shortenurlservice.infrastructure.jpa.ShortenUrlJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CheckIndex {
    private final ShortenUrlJpaRepository shortenUrlJpaRepository;
    private final DatabaseShortenUrlRepository databaseShortenUrlRepository;
    private final ShortenUrlService shortenUrlService;

    public CheckIndex(ShortenUrlJpaRepository shortenUrlJpaRepository) {
        this.shortenUrlJpaRepository = shortenUrlJpaRepository;
        this.databaseShortenUrlRepository = new DatabaseShortenUrlRepository(shortenUrlJpaRepository);
        this.shortenUrlService = new ShortenUrlService(databaseShortenUrlRepository);
    }
    public void testToIndex() {

        String url = "http://example.com";

        for (int i = 0; i < 10000; i++) {
            shortenUrlService.createShortUrl(url);
        }
    }
}
