package org.atanasov.wcds.web.servlets;

import org.atanasov.wcds.domain.models.service.UserLoginServiceModel;
import org.atanasov.wcds.domain.models.service.UserServiceModel;
import org.atanasov.wcds.service.UserService;
import org.atanasov.wcds.util.ModelMapperWrapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

import static org.atanasov.wcds.constants.Constants.*;

@WebServlet(USER_LOGIN_URL)
public class UserLoginServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserLoginServlet.class.getName());

    private final UserService userService;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public UserLoginServlet(UserService userService,
                            ModelMapperWrapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginServiceModel model = modelMapper.map(req, UserLoginServiceModel.class);
        try {
            UserServiceModel userServiceModel = userService.login(model);
            HttpSession session = req.getSession();
            session.setAttribute("userId", userServiceModel.getId());
            session.setAttribute("username", userServiceModel.getUsername());
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            req.setAttribute(ERR_MSG_ATTR, ex.getMessage());
            req.getRequestDispatcher(LOGIN_VIEW).forward(req, resp);
            return;
        }
        resp.sendRedirect(HOME_URL);
    }
}
