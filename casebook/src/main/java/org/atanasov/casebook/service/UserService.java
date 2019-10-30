package org.atanasov.casebook.service;

import org.atanasov.casebook.domain.models.service.UserLoginServiceModel;
import org.atanasov.casebook.domain.models.service.UserRegisterServiceModel;
import org.atanasov.casebook.domain.models.service.UserServiceModel;
import org.atanasov.casebook.domain.models.view.UserFriendsViewModel;
import org.atanasov.casebook.domain.models.view.UserHomeViewModel;
import org.atanasov.casebook.domain.models.view.UserProfileViewModel;

import java.util.List;

public interface UserService {

    void register(UserRegisterServiceModel model);

    UserServiceModel login(UserLoginServiceModel model);

    List<UserHomeViewModel> getAllOtherUsers(String currentUserId);

    UserFriendsViewModel getUserFriends(String id);

    UserProfileViewModel getUserProfile(String id);

    void addFriend(String userId, String loggedUserId);

    void removeFriend(String userId, String loggedUserId);
}
