package az.blogoot.domain;

import java.io.Serializable;

/**
 * Email
 */
public class Email implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3121560433411780441L;
    private long id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private User user;

    public Email() {
        this.id = 0L;
        this.body = "";
        this.from = "";
        this.subject = "";
        this.to = "";
        this.user = new User();

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Email [body=" + body + ", from=" + from + ", id=" + id + ", subject=" + subject + ", to=" + to + "]";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}