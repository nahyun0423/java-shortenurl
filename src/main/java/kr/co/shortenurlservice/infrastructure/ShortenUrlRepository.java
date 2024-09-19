package kr.co.shortenurlservice.infrastructure;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShortenUrlRepository {
    private Map<String, String> urlStorage = new HashMap<>();

    public void save(String shortKey, String originalUrl) {
        urlStorage.put(shortKey, originalUrl);
    }
}
