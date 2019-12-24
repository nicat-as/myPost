package az.blogoot.repository.impl.jdbc.sql;

/**
 * TokenSql
 */
public class TokenSql {

    public static final String SAVE_TOKEN = "insert into token (token, user_id, generation_date, expiration_date) values (:token, :user, :generationDate, :expirationDate)";
}