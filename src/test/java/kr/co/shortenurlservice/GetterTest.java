package kr.co.shortenurlservice;

import kr.co.shortenurlservice.getterandlombok.UseGetter;
import kr.co.shortenurlservice.getterandlombok.UseLombok;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetterTest {
    @Test
    public void testGetterAndLombok() {
        String originalUrl = "http://example.com";
        String shortKey = "abcde";
        int redirectCount = 5;

        UseGetter useGetter = new UseGetter();
        useGetter.setOriginalUrl(originalUrl);
        useGetter.setShortKey(shortKey);
        useGetter.setRedirectCount(redirectCount);

        UseLombok useLombok = new UseLombok();
        useLombok.setOriginalUrl(originalUrl);
        useLombok.setShortKey(shortKey);
        useLombok.setRedirectCount(redirectCount);


        assertEquals(useLombok.getOriginalUrl(), useGetter.getOriginalUrl());
        assertEquals(useLombok.getShortKey(), useGetter.getShortKey());
        assertEquals(useLombok.getRedirectCount(), useGetter.getRedirectCount());
    }
}
