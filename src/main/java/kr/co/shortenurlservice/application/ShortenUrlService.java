package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlDto createShortUrl(String url) {
        ShortenUrl shortenUrl = new ShortenUrl(url);
        shortenUrlRepository.save(shortenUrl.getShortKey(), shortenUrl.getOriginalUrl());
        ShortenUrlDto savedShortenUrlDto = ShortenUrlDto.toDto(shortenUrl);
        return savedShortenUrlDto;
    }

    public ShortenUrlDto findByKey(String shortKey) {
    }
}
