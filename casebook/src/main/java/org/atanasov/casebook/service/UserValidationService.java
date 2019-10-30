package org.atanasov.casebook.service;

public interface UserValidationService {
    boolean canCreateUser(String username, String gender, String password, String confirmPassword);

    boolean arePasswordsMatching(String password, String confirmPassword);

    boolean isGenderValid(String gender);

    boolean isNotUsernameTaken(String username);
}
