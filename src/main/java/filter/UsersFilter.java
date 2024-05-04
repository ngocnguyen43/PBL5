package filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.IUserPermissionDAO;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.contants.EndPoint;
import utils.exceptions.Exception;
import utils.exceptions.api.MethodNotAllowedException;
import utils.response.Message;
import utils.response.Meta;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/users"})
public class UsersFilter implements Filter {
    @Inject
    private IUserPermissionDAO iUserPermissionDAO;

    private final Logger logger = Logger.getLogger(UsersFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        try {

            List<String> allowedMethods = Arrays.asList("GET", "POST");
            if (!allowedMethods.contains(httpRequest.getMethod())) {
                throw new MethodNotAllowedException();
            }
            filterChain.doFilter(httpRequest, httpResponse);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            if (e instanceof utils.exceptions.Exception) {
                Meta meta = new Meta.Builder(((Exception) e).getStatusCode()).withErrCode(((Exception) e).getErrorCode()).withError(e.getMessage()).build();
                httpResponse.setStatus(((Exception) e).getStatusCode());
                httpResponse.getWriter().print(new ObjectMapper().writeValueAsString(new Message.Builder(meta).build()));
                httpResponse.getWriter().flush();
                return;
            }
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}