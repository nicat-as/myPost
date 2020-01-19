package az.blogoot.domain;
import java.util.Arrays;

/**
 * UserRole
 */
public enum UserRole {
    USER(1), ADMIN(2);

    private int value;
    private String page;
    private int priority;


    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserRole fromValue(int type) {
        return Arrays.stream(values()).filter(role -> role.value == type).findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid user role " + type));
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    
}