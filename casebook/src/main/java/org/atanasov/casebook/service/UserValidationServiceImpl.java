package org.atanasov.casebook.service;

import org.atanasov.casebook.domain.entities.User;
import org.atanasov.casebook.domain.enums.Gender;
import org.atanasov.casebook.repository.UserRepository;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.atanasov.casebook.constants.Constants.USERNAME_ATTR;

public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    @Inject
    public UserValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean canCreateUser(String username, String gender, String password, String confirmPassword) {
        return isGenderValid(gender)
                && arePasswordsMatching(password, confirmPassword)
                && isNotUsernameTaken(username);
    }

    @Override
    public boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Override
    public boolean isGenderValid(String gender) {
        return List.of(Gender.values())
                .stream()
                .anyMatch(g -> g.name().equalsIgnoreCase(gender));
    }

    @Override
    public boolean isNotUsernameTaken(String username) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(USERNAME_ATTR, username);
        return userRepository.findByNamedQueryAndNamedParams(User.FIND_BY_USERNAME, params).isEmpty();
    }
}
