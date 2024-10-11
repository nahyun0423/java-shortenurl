package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class MapShortenShortenUrlRepository implements ShortenUrlRepository {
    private Map<String, ShortenUrl> urlStorage = new HashMap<>();

    public Object save(ShortenUrl shortenUrl) {
        urlStorage.put(shortenUrl.getShortKey(), shortenUrl);
        return null;
    }

    public ShortenUrl findByKey(String shortKey) {
        return urlStorage.get(shortKey);
    }

    @Override
    public List<ShortenUrlDto> findAll() {
        List<ShortenUrlDto> shortenUrlDtos = new ArrayList<>();
        for(String i : urlStorage.entrySet()) {
            shortenUrlDtos.add(ShortenUrlDto.toDto(urlStorage.get(i)));
        }
        return shortenUrlDtos;
    }
}
