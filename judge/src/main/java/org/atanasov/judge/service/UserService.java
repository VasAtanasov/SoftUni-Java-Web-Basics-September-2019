package org.atanasov.judge.service;


import org.atanasov.judge.domain.models.service.UserLoginServiceModel;
import org.atanasov.judge.domain.models.service.UserRegisterServiceModel;
import org.atanasov.judge.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel model);

    UserServiceModel login(UserLoginServiceModel model);
}
