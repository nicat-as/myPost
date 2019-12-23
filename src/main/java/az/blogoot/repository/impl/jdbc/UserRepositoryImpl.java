package az.blogoot.repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import az.blogoot.domain.User;
import az.blogoot.repository.UserRepository;
import az.blogoot.repository.impl.jdbc.sql.UserSql;

/**
 * UserRepositoryImpl
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User addUser(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName()).addValue("lastname", user.getLastname())
                .addValue("email", user.getEmail()).addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int count = jdbcTemplate.update(UserSql.ADD_USER, params, keyHolder);

        if(count>0){
            user.setId(keyHolder.getKey().longValue());
        }else{
            throw new RuntimeException("User not added = "+ user);
        }

        return user;
    }

}