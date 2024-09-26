package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShortenUrlRepository {
    private Map<String, ShortenUrl> urlStorage = new HashMap<>();

    public void save(ShortenUrl shortenUrl) {
        urlStorage.put(shortenUrl.getShortKey(), shortenUrl);
    }

    public ShortenUrl findByKey(String shortKey) {
        return urlStorage.get(shortKey);
    }
}
