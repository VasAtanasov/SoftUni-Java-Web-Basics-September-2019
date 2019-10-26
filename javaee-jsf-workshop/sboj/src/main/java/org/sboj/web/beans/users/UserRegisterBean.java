package org.sboj.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sboj.domain.models.service.UserRegisterServiceModel;
import org.sboj.service.MessageService;
import org.sboj.service.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

@Model
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBean {

    private static final Logger logger = Logger.getLogger(UserRegisterBean.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    @NotNull(message = "Please ensure you enter your username")
    @Size(min = 2, message = "Username should be {min} symbols or more.")
    private String username;

    @NotNull(message = "You must enter a password")
    @Size(min = 3, message = "Password should be {min} symbols or more.")
    private String password;

    @NotNull(message = "You must enter a confirm password")
    @Size(min = 3, message = "Password should be {min} symbols or more.")
    private String confirmPassword;

    @Email(message = "You have entered an invalid email")
    @NotNull(message = "You must enter an email")
    private String email;

    public void register() {
        UserRegisterServiceModel model = UserRegisterServiceModel.builder()
                .username(username)
                .password(password)
                .confirmPassword(confirmPassword)
                .email(email)
                .build();

        try {
            userService.register(model);
        } catch (IllegalArgumentException iae) {
            logger.severe(iae.getMessage());
            messageService.addMessage(iae.getMessage());
        }
    }
}
