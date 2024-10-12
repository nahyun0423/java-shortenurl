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
public class ShortenUrlJpaRepositoryImpl implements ShortenUrlRepository {

    private final ShortenUrlJpaRepository shortenUrlJpaRepository;

    @Override
    public void save(ShortenUrl shortenUrl) {
    }

    @Override
    public ShortenUrl findByKey(String shortKey) {
        return shortenUrlJpaRepository.findByKey(shortKey);
    }

    @Override
    public List<ShortenUrl> findAll() {
        return shortenUrlJpaRepository.findAll();
    }
}
