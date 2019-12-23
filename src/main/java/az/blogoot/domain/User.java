package az.blogoot.domain;

import java.io.Serializable;

/**
 * User
 */
public class User extends Base implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4926221029173570220L;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private UserStatus userStatus;

    public User() {
        this.name = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
        this.userStatus = UserStatus.PENDING;
    }

  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + getId() + ", lastname=" + lastname + ", name=" + name + ", userStatus="
                + userStatus + "]";
    }

}