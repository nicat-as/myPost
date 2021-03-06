package az.blogoot.repository.impl.jdbc.sql;

/**
 * UserSql
 */
public class UserSql {
    public static final String ADD_USER = "insert into user (name, lastname, email, password) values(:name, :lastname, :email, :password)";

    public static final String CHECK_USER_BY_EMAIL = "select id,name, lastname, email, password, user_status from user where email=:email and status=1";

    public static final String ACTIVATE_USER = "update user u " + "inner join " + "token t " + "on u.id= "
            + "t.user_id set u.user_status=1 " + " where t.token=" + ":token and u.status=1" + " and t.status=1"
            + " and datediff(t.expiration_date, t.generation_date)>0";

    public static final String IS_DUPLICATE = "select count(id) from user where email= :email";

    public static final String ADD_ROLE = "insert into user_role (user_id, role_id) values (:user, :role)";

    public static final String GET_ROLES = "select ur.role_id, r.page, r.priority from user_role ur inner join role r on r.id = ur.role_id where ur.user_id=:id order by r.priority";

}