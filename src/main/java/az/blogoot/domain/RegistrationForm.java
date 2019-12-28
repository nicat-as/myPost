package az.blogoot.domain;

import java.io.Serializable;

/**
 * RegistrationForm
 */
public class RegistrationForm implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2261830267101821123L;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean acceptTerms;

    public RegistrationForm() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    @Override
    public String toString() {
        return "RegistrationForm [acceptTerms=" + acceptTerms + ", confirmPassword=" + confirmPassword + ", email="
                + email + ", lastname=" + lastname + ", name=" + name + ", password=" + password + "]";
    }

}