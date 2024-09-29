package kr.co.shortenurlservice.application;

import kr.co.shortenurlservice.domain.ShortenUrl;
import kr.co.shortenurlservice.infrastructure.ShortenUrlRepository;
import kr.co.shortenurlservice.presentation.ShortenUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShortenUrlService {

    private ShortenUrlRepository shortenUrlRepository;

    @Autowired
    public ShortenUrlService(ShortenUrlRepository shortenUrlRepository) {
        this.shortenUrlRepository = shortenUrlRepository;
    }

    public ShortenUrlDto createShortUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL 입력이 잘못되었습니다");
        }

        ShortenUrl shortenUrl = new ShortenUrl(url);
        shortenUrlRepository.save(shortenUrl);
        ShortenUrlDto savedShortenUrlDto = ShortenUrlDto.toDto(shortenUrl);
        return savedShortenUrlDto;
    }

    public ShortenUrlDto findByKey(String shortKey) {
        ShortenUrl shortenUrl = shortenUrlRepository.findByKey(shortKey);

        if (shortenUrl == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 short key 입니다.");
        }

        return ShortenUrlDto.toDto(shortenUrl);
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
