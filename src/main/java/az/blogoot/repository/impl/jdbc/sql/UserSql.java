package az.blogoot.repository.impl.jdbc.sql;

/**
 * UserSql
 */
public class UserSql {
    public static final String ADD_USER ="insert into user (name, lastname, email, password) values(:name, :lastname, email, password)";
    
}