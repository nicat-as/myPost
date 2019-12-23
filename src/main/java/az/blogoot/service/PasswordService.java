package az.blogoot.service;

/**
 * PasswordService
 */
public interface PasswordService {

    String encrypt(String password);

    boolean checkPassword(String password, String stored);
}