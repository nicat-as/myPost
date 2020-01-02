package az.blogoot.repository;

/**
 * ValidationRepository
 */
public interface ValidationRepository {

    boolean isDuplicate(String email);
    
}