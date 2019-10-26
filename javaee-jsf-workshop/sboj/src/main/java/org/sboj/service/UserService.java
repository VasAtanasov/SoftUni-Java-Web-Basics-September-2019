package org.sboj.service;


import org.sboj.domain.models.service.UserLoginServiceModel;
import org.sboj.domain.models.service.UserRegisterServiceModel;
import org.sboj.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel model);

    UserServiceModel login(UserLoginServiceModel model);
}
