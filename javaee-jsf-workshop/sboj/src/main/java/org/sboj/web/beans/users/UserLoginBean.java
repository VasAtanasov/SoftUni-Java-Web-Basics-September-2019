package org.sboj.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sboj.domain.models.service.UserLoginServiceModel;
import org.sboj.domain.models.service.UserServiceModel;
import org.sboj.service.MessageService;
import org.sboj.service.RedirectService;
import org.sboj.service.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.logging.Logger;

import static org.sboj.constants.Constants.*;

@Model
@Getter
@Setter
@NoArgsConstructor
public class UserLoginBean {

    private Logger logger = Logger.getLogger(UserLoginBean.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private RedirectService redirectService;

    @Size(min = 2, message = "Username should be {min} symbols or more.")
    @NotNull(message = "Please ensure you enter your username")
    private String username;

    @Size(min = 3, message = "Password should be {min} symbols or more.")
    @NotNull(message = "You must enter a password")
    private String password;

    public void login() {
        UserLoginServiceModel model = UserLoginServiceModel.builder()
                .username(username)
                .password(password)
                .build();

        try {
            UserServiceModel serviceModel = userService.login(model);
            addUserToSession(serviceModel);
            redirectService.redirect(HOME_URL);

        } catch (IllegalArgumentException | IOException iae) {
            logger.severe(iae.getMessage());
            messageService.addMessage(iae.getMessage());
        }
    }

    private void addUserToSession(UserServiceModel user) {
        HttpSession session = request.getSession();
        session.setAttribute(USER_ID_ATTR, user.getId());
        session.setAttribute(USERNAME_ATTR, user.getUsername());
    }
}
