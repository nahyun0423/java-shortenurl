package kr.co.shortenurlservice.infrastructure.jpa;

import kr.co.shortenurlservice.domain.ShortenUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
@Profile("prod")
public class ShortenUrlRepositoryImpl implements ShortenUrlRepository {

    private final ShortenUrlJpaRepository shortenUrlJpaRepository;

    @Override
    public void save(ShortenUrl shortenUrl) {
        shortenUrlJpaRepository.save(shortenUrl);
    }

    @Override
    public ShortenUrl findByShortKey(String shortKey) {
        return shortenUrlJpaRepository.findByShortKey(shortKey);
    }

    @Override
    public List<ShortenUrl> findAll() {
        return shortenUrlJpaRepository.findAll();
    }
}
