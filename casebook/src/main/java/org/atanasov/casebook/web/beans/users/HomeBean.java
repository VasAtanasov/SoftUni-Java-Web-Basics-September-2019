package org.atanasov.casebook.web.beans.users;

import lombok.Getter;
import org.atanasov.casebook.domain.models.view.UserHomeViewModel;
import org.atanasov.casebook.service.MessageService;
import org.atanasov.casebook.service.RedirectService;
import org.atanasov.casebook.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.atanasov.casebook.constants.Constants.HOME_URL;
import static org.atanasov.casebook.constants.Constants.USER_ID_ATTR;

@Model
@Getter
public class HomeBean {

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private MessageService messageService;

    @Inject
    private RedirectService redirectService;

    private List<UserHomeViewModel> users;

    @PostConstruct
    private void init() {
        String currentUserId = (String) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get(USER_ID_ATTR);

        users = new ArrayList<>() {{
            addAll(userService.getAllOtherUsers(currentUserId));
        }};
    }

    public void addFriend(String userId, String loggedUserId) {
        try {
            userService.addFriend(userId, loggedUserId);
            redirectService.redirect(HOME_URL);
        } catch (IOException e) {
            messageService.addMessage("Redirect failed");
        } catch (IllegalArgumentException iae) {
            messageService.addMessage(iae.getMessage());
        }
    }
}
