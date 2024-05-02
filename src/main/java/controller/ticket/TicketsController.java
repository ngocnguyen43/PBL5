package controller.ticket;

import dto.TicketDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ITicketService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/tickets"})
@MultipartConfig
public class TicketsController extends HttpServlet {
    @Inject
    private ITicketService iTicketService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TicketDto dto = Helper.of(req.getReader()).toModel(TicketDto.class);
        ErrorHandler.handle(resp, () -> this.iTicketService.CreateOne(dto));
    }
}
