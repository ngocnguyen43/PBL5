package controller.roles;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.interfaces.IUserService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/my_role"})
public class RoleController extends HttpServlet {
    @Inject
    private IUserService iUserService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getAttribute("user");
        ErrorHandler.handle(resp, () -> this.iUserService.FindRole(user.getUserId()));
    }
}
