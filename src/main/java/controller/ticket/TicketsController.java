package controller.ticket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TicketDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.interfaces.ITicketService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/tickets"})
@MultipartConfig
public class TicketsController extends HttpServlet {
    @Inject
    private ITicketService iTicketService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TypeReference<List<TicketDto>> dtosType = new TypeReference<>() {
        };
        User user = (User) req.getAttribute("user");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
        List<TicketDto> dtos = Helper.of(req.getReader()).toModel(dtosType);
        dtos.forEach(e -> {
            e.setUserId(user.getUserId());
        });
        ErrorHandler.handle(resp, () -> this.iTicketService.BulkCreate(dtos, user.getUserId()));
    }
}
