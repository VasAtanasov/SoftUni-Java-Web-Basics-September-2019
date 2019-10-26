package org.atanasov.sboj.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.sboj.domain.models.service.UserLoginServiceModel;
import org.atanasov.sboj.domain.models.service.UserServiceModel;
import org.atanasov.sboj.service.MessageService;
import org.atanasov.sboj.service.RedirectService;
import org.atanasov.sboj.service.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.logging.Logger;

import static org.atanasov.sboj.constants.Constants.*;

@Model
@Getter
@Setter
@NoArgsConstructor
public class UserLoginBean {

    private Logger logger = Logger.getLogger(UserLoginBean.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private RedirectService redirectService;

    @Inject
    private MessageService messageService;

    @Inject
    private HttpServletRequest request;

    @Size(min = 2, message = "Username should be {min} symbols or more.")
    @NotNull(message = "Please ensure you enter your username")
    private String username;

    @Size(min = 3, message = "Password should be {min} symbols or more.")
    @NotNull(message = "You must enter a password")
    private String password;

    public void login() throws IOException {
        UserLoginServiceModel model = UserLoginServiceModel.builder()
                .username(username)
                .password(password)
                .build();

        try {
            UserServiceModel serviceModel = userService.login(model);
            addUserToSession(serviceModel);
            redirectService.redirect(HOME_URL);
        } catch (IllegalArgumentException iae) {
            messageService.addMessage(iae.getMessage());
            logger.severe(iae.getMessage());
        }
    }

    private void addUserToSession(UserServiceModel user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID_ATTR, user.getId());
        session.setAttribute(USERNAME_ATTR, user.getUsername());
    }
}
