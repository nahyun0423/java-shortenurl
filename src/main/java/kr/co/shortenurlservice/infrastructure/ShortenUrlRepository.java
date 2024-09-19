package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShortenUrlRepository {
    private Map<String, String> urlStorage = new HashMap<>();

    public void save(String shortKey, String originalUrl) {
        urlStorage.put(shortKey, originalUrl);
    }

    public ShortenUrl findByKey(String shortKey){
        return new ShortenUrl(shortKey, urlStorage.get(shortKey));
    }
}
