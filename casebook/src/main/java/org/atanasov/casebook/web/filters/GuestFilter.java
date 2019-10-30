package org.atanasov.casebook.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.atanasov.casebook.constants.Constants.*;


@WebFilter(
        {
                HOME_URL,
                LOGOUT_URL,
                FRIENDS_URL,
                FRIENDS_ADD_URL,
                FRIENDS_REMOVE_URL,
                PROFILE_URL,
        }
)
public class GuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(USERNAME_ATTR) == null) {
            response.sendRedirect(LOGIN_URL);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
