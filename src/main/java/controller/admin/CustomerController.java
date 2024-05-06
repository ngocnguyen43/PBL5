package controller.admin;

import dto.UserDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IUserService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/customers/*"})
@MultipartConfig
public class CustomerController extends HttpServlet {
    @Inject
    private IUserService iUserService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        ErrorHandler.handle(resp, () -> this.iUserService.FindOne(id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        ErrorHandler.handle(resp, () -> this.iUserService.DeleteOne(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        UserDto userDto = Helper.paramsToString(req.getParameterMap()).toModel(UserDto.class);
        ErrorHandler.handle(resp, () -> this.iUserService.UpdateOne(id, userDto));
    }
}
