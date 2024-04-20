package controller.booking;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IBookingService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/bookings/*"})
public class BookingController extends HttpServlet {
    @Inject
    private IBookingService iBookingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path != null && path.split("/").length > 0){
            String id = path.split("/")[1];
            ErrorHandler.handle(resp,()->this.iBookingService.GetOneById(id));
        }else{

            ErrorHandler.handle(resp,()->this.iBookingService.GetAll());
        }

    }
}
