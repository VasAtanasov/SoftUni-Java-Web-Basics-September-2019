package org.atanasov.judge.service;

import org.atanasov.judge.domain.entities.User;
import org.atanasov.judge.domain.models.service.UserLoginServiceModel;
import org.atanasov.judge.domain.models.service.UserRegisterServiceModel;
import org.atanasov.judge.domain.models.service.UserServiceModel;
import org.atanasov.judge.repository.UserRepository;
import org.atanasov.judge.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.atanasov.judge.constants.Constants.PASSWORD_ATTR;
import static org.atanasov.judge.constants.Constants.USERNAME_ATTR;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    private static final String INVALID_EMAIL = "Invalid or taken email.";
    private static final String PASSWORD_MATCH_ERROR = "Passwords do not match.";
    private static final String USERNAME_TAKEN = "Username is already taken.";
    private static final String INVALID_CREDENTIALS = "Invalid username or password please try again.";

    private final UserRepository repository;
    private final UserValidationService validationService;
    private final ModelMapperWrapper modelMapper;
    private final HashingService hashingService;

    @Inject
    public UserServiceImpl(UserRepository repository,
                           UserValidationService validationService,
                           ModelMapperWrapper modelMapper1,
                           HashingService hashingService) {
        this.repository = repository;
        this.validationService = validationService;
        this.modelMapper = modelMapper1;
        this.hashingService = hashingService;
    }

    @Override
    public void register(UserRegisterServiceModel model) {
        if (!validationService.isEmailValid(model.getEmail())) {
            throw new IllegalArgumentException(INVALID_EMAIL);
        }

        if (!validationService.arePasswordsMatching(model.getPassword(), model.getConfirmPassword())) {
            throw new IllegalArgumentException(PASSWORD_MATCH_ERROR);
        }

        if (!validationService.isNotUsernameTaken(model.getUsername())) {
            throw new IllegalArgumentException(USERNAME_TAKEN);
        }

        model.setPassword(hashingService.hash(model.getPassword()));
        repository.save(modelMapper.map(model, User.class));
    }

    @Override
    public UserServiceModel login(UserLoginServiceModel model) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(USERNAME_ATTR, model.getUsername());
        params.put(PASSWORD_ATTR, hashingService.hash(model.getPassword()));

        UserServiceModel userServiceModel = repository.findByNamedQueryAndNamedParams(User.FIND_BY_USERNAME_AND_PASSWORD, params)
                .stream()
                .findFirst()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);

        if (userServiceModel == null) {
            logger.severe(INVALID_CREDENTIALS);
            throw new IllegalArgumentException(INVALID_CREDENTIALS);
        }

        return userServiceModel;
    }
}
