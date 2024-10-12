package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.jpa.ShortenUrlRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Profile("test")
public class MapShortenShortenUrlRepository implements ShortenUrlRepository {

    private Map<String, ShortenUrl> urlStorage = new HashMap<>();

    public void save(ShortenUrl shortenUrl) {
        urlStorage.put(shortenUrl.getShortKey(), shortenUrl);
    }

    public ShortenUrl findByKey(String shortKey) {
        return urlStorage.get(shortKey);
    }

    @Override
    public List<ShortenUrl> findAll() {
        return urlStorage.values().stream()
                .collect(Collectors.toList());
    }
}
