package controller.confirm;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.websocket.WebSocket;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IOrderService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.response.Message;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/confirm"})
public class ConfirmController extends HttpServlet {
    @Inject
    private IOrderService iOrderService;
    @Inject
    private WebSocket webSocket;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String order = req.getParameter("order");
        String id = req.getParameter("id");
        ErrorHandler.handle(resp, () -> {
            Message message = this.iOrderService.ConfirmOrder(order);
            webSocket.emitMessage(id, "paid success");
            return message;
        });
    }
}
