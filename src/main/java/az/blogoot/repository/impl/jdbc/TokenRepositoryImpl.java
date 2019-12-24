package az.blogoot.repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import az.blogoot.domain.Token;
import az.blogoot.repository.TokenRepository;
import az.blogoot.repository.impl.jdbc.sql.TokenSql;

/**
 * TokenRepositoryImpl
 */

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Token saveToken(Token token) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token.getToken()).addValue("user", token.getUser().getId())
                .addValue("generationDate", token.getGenerationDate())
                .addValue("expirationDate", token.getExpirationDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int count = jdbcTemplate.update(TokenSql.SAVE_TOKEN, params, keyHolder);
        System.out.println(count);
        if (count > 0) {
            token.getUser().setId(keyHolder.getKey().longValue());
        } else {
            throw new RuntimeException("Token couldn't add to db " + token);
        }

        return token;
    }

}