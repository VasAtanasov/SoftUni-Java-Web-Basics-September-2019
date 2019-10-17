package org.atanasov.wcds.service;

import org.atanasov.wcds.domain.entities.User;
import org.atanasov.wcds.domain.models.service.UserLoginServiceModel;
import org.atanasov.wcds.domain.models.service.UserRegisterServiceModel;
import org.atanasov.wcds.domain.models.service.UserServiceModel;
import org.atanasov.wcds.repository.UserRepository;
import org.atanasov.wcds.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

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
            throw new IllegalArgumentException("Invalid or taken email.");
        }

        if (!validationService.arePasswordsMatching(model.getPassword(), model.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        if (!validationService.isNotUsernameTaken(model.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        model.setPassword(hashingService.hash(model.getPassword()));
        repository.save(modelMapper.map(model, User.class));
    }

    @Override
    public UserServiceModel login(UserLoginServiceModel model) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", model.getUsername());
        params.put("password", hashingService.hash(model.getPassword()));

        UserServiceModel userServiceModel = repository.findByNamedQueryAndNamedParams(User.FIND_BY_USERNAME_AND_PASSWORD, params)
                .stream()
                .findFirst()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);

        if (userServiceModel == null) {
            throw new IllegalArgumentException("Invalid username or password please try again.");
        }

        return userServiceModel;
    }
}
