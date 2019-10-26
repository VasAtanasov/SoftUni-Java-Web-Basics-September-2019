package org.atanasov.sboj.service;


import org.atanasov.sboj.domain.models.service.UserLoginServiceModel;
import org.atanasov.sboj.domain.models.service.UserRegisterServiceModel;
import org.atanasov.sboj.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel model);

    UserServiceModel login(UserLoginServiceModel model);
}
