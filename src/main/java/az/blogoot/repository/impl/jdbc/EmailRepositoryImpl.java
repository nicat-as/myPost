package az.blogoot.repository.impl.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import az.blogoot.domain.Email;
import az.blogoot.repository.EmailRepository;
import az.blogoot.repository.impl.jdbc.mapper.EmailMapper;
import az.blogoot.repository.impl.jdbc.sql.EmailSql;

/**
 * EmailRepositoryImpl
 */

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private EmailMapper emailMapper;

    @Override
    public Email saveEmail(Email email) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("from", email.getFrom())
                .addValue("to", email.getTo()).addValue("subject", email.getSubject())
                .addValue("body", email.getBody())
                .addValue("userId", email.getUser().getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int count = jdbcTemplate.update(EmailSql.SAVE_EMAIL, params, keyHolder);

        if (count > 0) {
            long id = keyHolder.getKey().longValue();
            email.setId(id);
        } else {
            throw new RuntimeException("Email can't be saved");
        }
        return email;

    }

    @Override
    public List<Email> getEmail(int limit) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("limit", limit);

        List<Email> emailList = jdbcTemplate.query(EmailSql.GET_EMAIL_LIST, params, emailMapper);
        return emailList;
    }

}