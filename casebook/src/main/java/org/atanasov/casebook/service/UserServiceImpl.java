package org.atanasov.casebook.service;

import org.atanasov.casebook.domain.entities.User;
import org.atanasov.casebook.domain.models.service.UserLoginServiceModel;
import org.atanasov.casebook.domain.models.service.UserRegisterServiceModel;
import org.atanasov.casebook.domain.models.service.UserServiceModel;
import org.atanasov.casebook.domain.models.view.UserFriendsViewModel;
import org.atanasov.casebook.domain.models.view.UserHomeViewModel;
import org.atanasov.casebook.domain.models.view.UserProfileViewModel;
import org.atanasov.casebook.repository.UserRepository;
import org.atanasov.casebook.util.ModelMapperWrapper;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.atanasov.casebook.constants.Constants.PASSWORD_ATTR;
import static org.atanasov.casebook.constants.Constants.USERNAME_ATTR;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private static final String PASSWORD_MATCH_ERROR = "Passwords do not match.";
    private static final String USERNAME_TAKEN = "Username is already taken.";
    private static final String INVALID_CREDENTIALS = "Invalid username or password please try again.";
    private static final String INVALID_GENDER = "Invalid gender value. Gender should be \"Male\" or \"Female\".";

    private final UserRepository repository;
    private final UserValidationService validationService;
    private final ModelMapperWrapper modelMapper;
    private final HashingService hashingService;

    @Inject
    public UserServiceImpl(UserRepository repository,
                           UserValidationService validationService,
                           ModelMapperWrapper modelMapper,
                           HashingService hashingService) {
        this.repository = repository;
        this.validationService = validationService;
        this.modelMapper = modelMapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(UserRegisterServiceModel model) {
        if (!validationService.isGenderValid(model.getGender())) {
            throw new IllegalArgumentException(INVALID_GENDER);
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

    @Override
    public List<UserHomeViewModel> getAllOtherUsers(String currentUserId) {
        User currentUser = repository.find(currentUserId);
        Map<String, Object> params = new LinkedHashMap<>() {{
            put("id", currentUserId);
            put("user", currentUser);
        }};

        List<User> users = repository.findByNamedQueryAndNamedParams(User.FIND_OTHER_USERS, params);

        return users.stream()
                .map(user -> modelMapper.map(user, UserHomeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserFriendsViewModel getUserFriends(String id) {
        return modelMapper.map(repository.find(id), UserFriendsViewModel.class);
    }

    @Override
    public UserProfileViewModel getUserProfile(String id) {
        return modelMapper.map(repository.find(id), UserProfileViewModel.class);
    }

    @Override
    public void addFriend(String userId, String loggedUserId) {
        User user = repository.find(userId);
        User loggedUser = repository.find(loggedUserId);

        if (user == null || loggedUser == null) {
            throw new IllegalArgumentException("No user does not exists.");
        }
        loggedUser.addFriend(user);
        repository.merge(user);
        repository.merge(loggedUser);
    }

    @Override
    public void removeFriend(String userId, String loggedUserId) {
        User user = repository.find(userId);
        User loggedUser = repository.find(loggedUserId);

        if (user == null || loggedUser == null) {
            throw new IllegalArgumentException("No user does not exists.");
        }
        loggedUser.removeFriend(user);
        repository.merge(user);
        repository.merge(loggedUser);
    }

}
