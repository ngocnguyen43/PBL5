package controller.auth;

import dto.CustomerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import utils.contants.EndPoint;
import utils.helper.Helper;

import java.io.IOException;
@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/auth/register"})
@MultipartConfig
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto dto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
        System.out.println(new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(dto));
    }
}
