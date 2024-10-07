package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenUrlRepository {
    void save(ShortenUrl shortenUrl);
    ShortenUrl findByKey(String shortKey);
}