package controller.scheduleRequests;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import utils.contants.EndPoint;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.ADMIN + "/schedule_requests/*"})
@MultipartConfig
public class ScheduleRequestController extends HttpServlet {
}
