package controller.station;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IStationService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/stations"})
public class StationsController extends HttpServlet {
    @Inject
    private IStationService iStationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("name");
        ErrorHandler.handle(resp, () -> this.iStationService.FindAll(query));
    }

}
