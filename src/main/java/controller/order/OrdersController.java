package controller.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.interfaces.IOrderService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@MultipartConfig
@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/orders"})
public class OrdersController extends HttpServlet {
    @Inject
    private IOrderService iOrderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object userId = req.getAttribute("userId");
        User user = (User) req.getAttribute("user");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
        ErrorHandler.handle(resp, () -> this.iOrderService.FindAllOrders(user));
    }

}
