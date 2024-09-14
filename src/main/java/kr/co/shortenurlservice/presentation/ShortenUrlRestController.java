package kr.co.shortenurlservice.presentation;

import kr.co.shortenurlservice.application.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ShortenUrlRestController {

    @RestController
    @RequestMapping("/api/shortener")
    public class UrlShortenerController {

        private ShortenUrlService shortenUrlService;

        @Autowired
        public UrlShortenerController(ShortenUrlService shortenUrlService) {
            this.shortenUrlService = shortenUrlService;
        }

        @RequestMapping(value = "/shorten", method = RequestMethod.POST)
        public ShortenUrlDto shortenUrl(@RequestParam String url) {
            String shortKey = shortenUrlService.createShortUrl(url);
            return shortenUrlService.add(shortKey);
        }

        @RequestMapping(value = "/{shortKey}", method = RequestMethod.GET)
        public ShortenUrlDto redirectUrl(@PathVariable String shortKey) {



        }
}
