package org.atanasov.wcds.service;

import org.atanasov.wcds.domain.models.service.UserLoginServiceModel;
import org.atanasov.wcds.domain.models.service.UserRegisterServiceModel;
import org.atanasov.wcds.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterServiceModel userServiceModel);

    UserServiceModel login(UserLoginServiceModel map);
}
