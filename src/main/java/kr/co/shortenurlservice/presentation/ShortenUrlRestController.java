package kr.co.shortenurlservice.presentation;

import jakarta.validation.Valid;
import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ShortenUrlRestController {

    private ShortenUrlService shortenUrlService;

    @Autowired
    public ShortenUrlRestController(ShortenUrlService shortenUrlService) {
        this.shortenUrlService = shortenUrlService;
    }

    //입력
    @RequestMapping(value = "/shortenUrl", method = RequestMethod.POST)
    public ResponseEntity<ShortenUrlDto> createShortenUrl(@Valid @RequestBody ShortenUrlRequestDto requestDto) {
        ShortenUrlDto shortenUrlCreateDto = shortenUrlService.createShortUrl(requestDto.getOriginalUrl());
        return ResponseEntity.ok(shortenUrlCreateDto);
    }

    //조회
    @RequestMapping(value = "/check/{shortKey}", method = RequestMethod.GET)
    public ShortenUrlDto findShortenUrl(@PathVariable String shortKey) {
        return shortenUrlService.findByKey(shortKey);
    }

    //리다이렉트
    @RequestMapping(value = "/{shortKey}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectUrl(@PathVariable String shortKey) {
        String originalUrl = shortenUrlService.redirectUrl(shortKey);
        if (originalUrl != null) {
            return ResponseEntity.status(302).header("Location", originalUrl).build();
        }
        return ResponseEntity.notFound().build();
    }
}

