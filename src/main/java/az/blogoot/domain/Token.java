package az.blogoot.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Token
 */
public class Token extends Base implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7608139058191877687L;
    private String token;
    private User user;
    private LocalDateTime generationDate;
    private LocalDateTime expirationDate;

    public Token() {
        this.token = "";
        this.user = new User();
        this.generationDate = null;
        this.expirationDate = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDateTime generationDate) {
        this.generationDate = generationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

}