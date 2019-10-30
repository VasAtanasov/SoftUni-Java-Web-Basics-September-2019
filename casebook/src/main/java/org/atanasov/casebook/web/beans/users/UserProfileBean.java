package org.atanasov.casebook.web.beans.users;

import lombok.Getter;
import org.atanasov.casebook.domain.models.view.UserProfileViewModel;
import org.atanasov.casebook.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Getter
@Model
public class UserProfileBean {

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    @Inject
    @RequestParameterMap
    private Map<String, String> requestMap;

    private UserProfileViewModel viewModel;

    @PostConstruct
    public void init() {
        String id = requestMap.get("profile_id");
        viewModel = userService.getUserProfile(id);
    }
}
