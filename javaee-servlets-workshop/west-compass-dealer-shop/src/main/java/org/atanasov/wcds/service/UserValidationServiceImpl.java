package org.atanasov.wcds.service;

import org.atanasov.wcds.domain.entities.User;
import org.atanasov.wcds.repository.UserRepository;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationServiceImpl implements UserValidationService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;

    @Inject
    public UserValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean canCreateUser(String username, String email, String password, String confirmPassword) {
        return isEmailValid(email)
                && arePasswordsMatching(password, confirmPassword)
                && isNotUsernameTaken(username);
    }

    @Override
    public boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find() && isNotEmailTaken(email);
    }

    private boolean isNotEmailTaken(String email) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("email", email);
        return userRepository.findByNamedQueryAndNamedParams(User.FIND_BY_EMAIL, params).isEmpty();
    }

    @Override
    public boolean isNotUsernameTaken(String username) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", username);
        return userRepository.findByNamedQueryAndNamedParams(User.FIND_BY_USERNAME, params).isEmpty();
    }
}
