package org.atanasov.casebook.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.atanasov.casebook.domain.models.service.UserRegisterServiceModel;
import org.atanasov.casebook.service.MessageService;
import org.atanasov.casebook.service.RedirectService;
import org.atanasov.casebook.service.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.logging.Logger;

import static org.atanasov.casebook.constants.Constants.LOGIN_URL;

@Model
@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBean {

    private Logger logger = Logger.getLogger(UserRegisterBean.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private RedirectService redirectService;

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

    @NotNull(message = "You must select gender")
    private String gender;

    public void register() {
        UserRegisterServiceModel serviceModel = UserRegisterServiceModel.builder()
                .username(username)
                .password(password)
                .confirmPassword(confirmPassword)
                .gender(gender)
                .build();

        try {
            userService.register(serviceModel);
            redirectService.redirect(LOGIN_URL);
        } catch (IllegalArgumentException | IOException iea) {
            logger.severe(iea.getMessage());
            messageService.addMessage(iea.getMessage());
        }
    }
}
