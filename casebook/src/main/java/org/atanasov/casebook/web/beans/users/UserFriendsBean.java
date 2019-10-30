package org.atanasov.casebook.web.beans.users;

import lombok.Getter;
import org.atanasov.casebook.domain.models.view.UserFriendsViewModel;
import org.atanasov.casebook.service.MessageService;
import org.atanasov.casebook.service.RedirectService;
import org.atanasov.casebook.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.atanasov.casebook.constants.Constants.*;

@Getter
@Model
public class UserFriendsBean {

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private MessageService messageService;

    @Inject
    private RedirectService redirectService;

    private UserFriendsViewModel viewModel;

    @PostConstruct
    public void init() {
        String currentUserId = (String) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get(USER_ID_ATTR);

        viewModel = userService.getUserFriends(currentUserId);
    }

    public void removeFriend(String userId, String loggedUserId) {
        try {
            userService.removeFriend(userId, loggedUserId);
            redirectService.redirect(FRIENDS_URL);
        } catch (IOException e) {
            messageService.addMessage("Redirect failed");
        } catch (IllegalArgumentException iae) {
            messageService.addMessage(iae.getMessage());
        }
    }
}
