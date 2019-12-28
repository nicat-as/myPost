package az.blogoot.repository.impl.jdbc.sql;

/**
 * UserSql
 */
public class UserSql {
    public static final String ADD_USER ="insert into user (name, lastname, email, password) values(:name, :lastname, :email, :password)";

    public static final String CHECK_USER_BY_EMAIL = "select id,name, lastname, email, password, user_status from user where email=:email and status=1";
    
}