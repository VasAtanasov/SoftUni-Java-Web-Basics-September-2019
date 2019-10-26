package org.sboj.service;

public interface UserValidationService {
    boolean canCreateUser(String username, String email, String password, String confirmPassword);

    boolean arePasswordsMatching(String password, String confirmPassword);

    boolean isEmailValid(String email);

    boolean isNotUsernameTaken(String username);
}
