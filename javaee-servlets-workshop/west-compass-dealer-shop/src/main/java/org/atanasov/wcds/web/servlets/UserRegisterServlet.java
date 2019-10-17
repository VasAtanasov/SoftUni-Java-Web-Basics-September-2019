package org.atanasov.wcds.web.servlets;


import org.atanasov.wcds.domain.models.service.UserRegisterServiceModel;
import org.atanasov.wcds.service.UserService;
import org.atanasov.wcds.util.ModelMapperWrapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static org.atanasov.wcds.constants.Constants.*;

@WebServlet(USER_REGISTER_URL)
public class UserRegisterServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(UserRegisterServlet.class.getName());

    private final UserService userService;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public UserRegisterServlet(UserService userService,
                               ModelMapperWrapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterServiceModel model = modelMapper.map(req, UserRegisterServiceModel.class);

        try {
            userService.register(model);
        } catch (Exception ex) {
            LOG.severe(ex.getMessage());
            req.setAttribute(ERR_MSG_ATTR, ex.getMessage());
            req.getRequestDispatcher(REGISTER_VIEW).forward(req, resp);
            return;
        }

        resp.sendRedirect(USER_LOGIN_URL);
    }
}
