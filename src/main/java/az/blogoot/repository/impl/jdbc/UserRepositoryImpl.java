package az.blogoot.repository.impl.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import az.blogoot.domain.User;
import az.blogoot.domain.UserRole;
import az.blogoot.repository.UserRepository;
import az.blogoot.repository.impl.jdbc.mapper.RoleMapper;
import az.blogoot.repository.impl.jdbc.mapper.UserMapper;
import az.blogoot.repository.impl.jdbc.sql.UserSql;

/**
 * UserRepositoryImpl
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User addUser(User user) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getName()).addValue("lastname", user.getLastname())
                .addValue("email", user.getEmail()).addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int count = jdbcTemplate.update(UserSql.ADD_USER, params, keyHolder);

        if (count > 0) {
            user.setId(keyHolder.getKey().longValue());
        } else {
            throw new RuntimeException("User not added = " + user);
        }

        return user;
    }

    @Override
    public User checkUserByEmail(String email) {

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("email", email);

        List<User> user = jdbcTemplate.query(UserSql.CHECK_USER_BY_EMAIL, params, userMapper);

        if (!user.isEmpty()) {
            return user.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean activateUser(String token) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("token", token);

        boolean isActive = false;

        int count = jdbcTemplate.update(UserSql.ACTIVATE_USER, paramMap);
        if (count > 0) {
            isActive = true;
        }
        return isActive;
    }

    @Override
    public void addRole(long user, int role) {
        MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("user", user).addValue("role", role);

        int count = jdbcTemplate.update(UserSql.ADD_ROLE, paramMap);
        if (count <= 0) {
            throw new RuntimeException("Role not added to user " + user + " and role " + role);
        }

    }

    @Override
    public List<UserRole> getRoles(long userId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("id", userId);

        List<UserRole> roleList = jdbcTemplate.query(UserSql.GET_ROLES, params, roleMapper);

        if(roleList.isEmpty()){
            throw new RuntimeException("there is no role for user " + userId);
        }

        return roleList;
    }

}