package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortenUrlRepository {
    Object save(ShortenUrl shortenUrl);
    ShortenUrl findByKey(String shortKey);
    List<ShortenUrl> findAll();
}