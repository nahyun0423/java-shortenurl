package kr.co.shortenurlservice.infrastructure.jpa;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortenUrlJpaRepository extends JpaRepository<ShortenUrl, Long> {
    ShortenUrl findByKey(String shortKey);
    List<ShortenUrl> findAll();
}
