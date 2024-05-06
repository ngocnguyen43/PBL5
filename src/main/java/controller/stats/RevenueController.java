package controller.stats;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IRevenueService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/stats/revenues"})
public class RevenueController extends HttpServlet {
    @Inject
    private IRevenueService iRevenueService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        ErrorHandler.handle(resp, () -> this.iRevenueService.FindAll(query));
    }
}
