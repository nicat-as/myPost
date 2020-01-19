package az.blogoot.repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import az.blogoot.repository.ValidationRepository;
import az.blogoot.repository.impl.jdbc.sql.UserSql;

/**
 * ValidationRepositoryImpl
 */

@Repository
public class ValidationRepositoryImpl implements ValidationRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean isDuplicate(String email) {
        boolean isDuplicate = false;

        MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("email", email);

        int count = jdbcTemplate.queryForObject(UserSql.IS_DUPLICATE, paramMap, Integer.class);
        if (count > 0) {
            isDuplicate = true;
        }

        return isDuplicate;
    }

}