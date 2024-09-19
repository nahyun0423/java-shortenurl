package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ShortenUrlRestController {

    @RestController
    public class UrlShortenerController {

        private ShortenUrlService shortenUrlService;

        @Autowired
        public UrlShortenerController(ShortenUrlService shortenUrlService) {
            this.shortenUrlService = shortenUrlService;
        }

        //입력
        @RequestMapping(value = "/shortenUrl", method = RequestMethod.POST)
        public ShortenUrlDto createShortenUrl(@RequestBody String originalUrl) {
           return shortenUrlService.createShortUrl(originalUrl);
        }

        //조회
        @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
        public ShortenUrlDto findShortenUrl(@PathVariable String shortKey) {
            return shortenUrlService.findByKey(shortKey);
        }
    }
}
