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
        shortenUrlRepository.save(shortenUrl);
        ShortenUrlDto savedShortenUrlDto = ShortenUrlDto.toDto(shortenUrl);
        return savedShortenUrlDto;
    }

    public ShortenUrlDto findByKey(String shortKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortKey);
        ShortenUrlDto shortenUrlDto = ShortenUrlDto.toDto(shortenUrl);
        return shortenUrlDto;
    }

    public String redirectUrl(String shortKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortKey);

        if (shortenUrl != null) {
            shortenUrl.increaseRedirectCount();
            shortenUrlRepository.save(shortenUrl);
            return shortenUrl.getOriginalUrl();
        }
        return null;
    }
}
