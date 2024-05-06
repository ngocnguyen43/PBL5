package controller.confirm;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IOrderService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + "/confirm/*"})
public class ConfirmController extends HttpServlet {
    @Inject
    private IOrderService iOrderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        ErrorHandler.handle(resp, () -> this.iOrderService.ConfirmOrder(id));
    }
}
