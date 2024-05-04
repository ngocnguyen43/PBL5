package controller.schedules;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import service.interfaces.IScheduleService;
import utils.contants.EndPoint;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/schedules/*"})
@MultipartConfig
public class ScheduleController extends HttpServlet {
    @Inject
    private IScheduleService iScheduleService;


}
