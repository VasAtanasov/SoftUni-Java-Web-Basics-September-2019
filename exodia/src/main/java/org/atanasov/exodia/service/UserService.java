package org.atanasov.exodia.service;


import org.atanasov.exodia.domain.models.service.UserLoginServiceModel;
import org.atanasov.exodia.domain.models.service.UserRegisterServiceModel;
import org.atanasov.exodia.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel model);

    UserServiceModel login(UserLoginServiceModel model);
}
