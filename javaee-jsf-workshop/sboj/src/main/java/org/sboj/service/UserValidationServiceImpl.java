package org.sboj.service;


import org.sboj.domain.entities.User;
import org.sboj.repository.UserRepository;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.sboj.constants.Constants.EMAIL_ATTR;
import static org.sboj.constants.Constants.USERNAME_ATTR;

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
        params.put(EMAIL_ATTR, email);
        return userRepository.findByNamedQueryAndNamedParams(User.FIND_BY_EMAIL, params).isEmpty();
    }

    @Override
    public boolean isNotUsernameTaken(String username) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(USERNAME_ATTR, username);
        return userRepository.findByNamedQueryAndNamedParams(User.FIND_BY_USERNAME, params).isEmpty();
    }
}
