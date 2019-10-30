package org.atanasov.casebook.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.atanasov.casebook.constants.Constants.*;


@WebFilter(
        {
                ROOT_URL,
                REGISTER_URL,
                LOGIN_URL
        }
)
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(USERNAME_ATTR) != null) {
            response.sendRedirect(HOME_URL);
            return;
        }

        filterChain.doFilter(request, response);
    }
}