package org.atanasov.wcds.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.atanasov.wcds.constants.Constants.HOME_URL;
import static org.atanasov.wcds.constants.Constants.HOME_VIEW;

@WebServlet(HOME_URL)
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HOME_VIEW).forward(req, resp);
    }
}
