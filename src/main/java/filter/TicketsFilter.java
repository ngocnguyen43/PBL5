package filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.IRoleDAO;
import dao.interfaces.IUserPermissionDAO;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserPermission;
import utils.contants.EndPoint;
import utils.contants.USER_PERMISSIONS;
import utils.exceptions.Exception;
import utils.exceptions.api.ForbiddenException;
import utils.exceptions.api.MethodNotAllowedException;
import utils.response.Message;
import utils.response.Meta;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/tickets"})
public class TicketsFilter implements Filter {
    private final Logger logger = Logger.getLogger(OrdersFilter.class.getName());
    @Inject
    private IRoleDAO iRoleDAO;
    @Inject
    private IUserPermissionDAO iUserPermissionDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        User user = (User) httpRequest.getAttribute("user");
        try {
            List<String> allowedMethods = List.of("POST");
            if (!allowedMethods.contains(httpRequest.getMethod())) {
                throw new MethodNotAllowedException();
            }
            httpRequest.setAttribute("userId", user.getUserId());
            if (httpRequest.getMethod().equals("POST")) {
                UserPermission userPermission = this.iUserPermissionDAO.FindOneByUserIdAndRoleName(user.getUserId(), USER_PERMISSIONS.CREATE_ORDER);
                if (userPermission == null) throw new ForbiddenException();
                filterChain.doFilter(httpRequest, httpResponse);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            if (e instanceof utils.exceptions.Exception) {
                Meta meta = new Meta.Builder(e.getStatusCode()).withErrCode(e.getErrorCode()).withError(e.getMessage()).build();
                httpResponse.setStatus(e.getStatusCode());
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
