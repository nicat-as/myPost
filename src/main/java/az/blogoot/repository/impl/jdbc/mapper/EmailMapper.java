package az.blogoot.repository.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import az.blogoot.domain.Email;

/**
 * EmailMapper
 */

@Component
public class EmailMapper implements RowMapper<Email> {

    @Override
    public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
       
        Email email = new Email();
        email.setId(rs.getLong("id"));
        email.setFrom(rs.getString("email_from"));
        email.setTo(rs.getString("email_to"));
        email.setSubject(rs.getString("email_subject"));
        email.setBody(rs.getString("body"));
        return email;
    }


    
}