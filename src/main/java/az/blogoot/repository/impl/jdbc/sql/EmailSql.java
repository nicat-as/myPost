package az.blogoot.repository.impl.jdbc.sql;

/**
 * EmailSql
 */
public class EmailSql {

    public static final String SAVE_EMAIL = "insert into email_queue (email_from, email_to, email_subject, body, user_id) values (:from, :to, :subject, :body, :userId)";
    public static final String GET_EMAIL_LIST = "select eq.id, eq.email_from, eq.email_to, eq.email_subject, eq.body from email_queue eq inner join user u on eq.user_id=u.id  where eq.status=1 and u.user_status=0 and u.status=1 order by eq.idate limit :limit";
}