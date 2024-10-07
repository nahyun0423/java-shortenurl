package kr.co.shortenurlservice.infrastructure;

import kr.co.shortenurlservice.domain.ShortenUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseShortenUrlRepository implements ShortenUrlRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DatabaseShortenUrlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(ShortenUrl shortenUrl) {
        String sql = "INSERT INTO shorten_url (original_url, short_key, redirect_count) VALUES (:originalUrl, :shortKey, :redirectCount)";
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(shortenUrl);

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public ShortenUrl findByKey(String shortKey) {
        String sql = "SELECT * FROM shorten_url WHERE short_key = :shortKey";
        SqlParameterSource parameterSource = new MapSqlParameterSource("shortKey", shortKey);

        List<ShortenUrl> results = namedParameterJdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(ShortenUrl.class));

        return results.isEmpty() ? null : results.get(0);
    }
}
