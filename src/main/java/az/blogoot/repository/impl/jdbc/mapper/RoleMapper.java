package az.blogoot.repository.impl.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import az.blogoot.domain.UserRole;

/**
 * RoleMapper
 */

@Component
public class RoleMapper implements RowMapper<UserRole> {

    @Override
    public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRole role = UserRole.fromValue(rs.getInt("role_id"));
        role.setPage(rs.getString("page"));
        role.setPriority(rs.getInt("priority"));
        return role;
    }

}