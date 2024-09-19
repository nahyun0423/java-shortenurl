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
        public ResponseEntity<ShortenUrlDto> createShortenUrl(@RequestBody String originalUrl) {
            ShortenUrlDto shortenUrlCreateDto = shortenUrlService.createShortUrl(originalUrl);
            return ResponseEntity.ok(shortenUrlCreateDto);
        }

        //조회
        @RequestMapping(value = "/{shortenUrlKey}", method = RequestMethod.GET)
        public ShortenUrlDto findShortenUrl(@PathVariable String shortKey) {
            return shortenUrlService.findByKey(shortKey);
        }

        //리다이렉트
        @RequestMapping(value = "/redirect/{shortenUrlKey}", method = RequestMethod.GET)
        public ResponseEntity<?> redirectUrl(@PathVariable String shortKey) {
            String originalUrl = shortenUrlService.redirectUrl(shortKey);
            return ResponseEntity.status(302).header("Location", originalUrl).build();
        }
    }
}
