package controller.auth;

import dto.CustomerDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IAuthService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.AUTH + "/register"})
@MultipartConfig
public class RegisterController extends HttpServlet {

    @Inject
    private IAuthService authService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto dto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
        ErrorHandler.handle(resp, () -> this.authService.Register(dto));
    }
}
