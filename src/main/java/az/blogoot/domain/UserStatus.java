package az.blogoot.domain;

import java.util.Arrays;

/**
 * UserStatus
 */
public enum UserStatus {
    PENDING(0), ACTIVE(1), DELETED(2), BLOCKED(3);

    private int value;

    private UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static UserStatus fromValue(int type) {
        return Arrays.stream(values()).filter(status -> status.value == type).findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid user status " + type));
    }

}