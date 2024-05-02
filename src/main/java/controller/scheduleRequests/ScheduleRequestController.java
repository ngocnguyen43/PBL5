package controller.scheduleRequests;

import dto.ScheduleRequestUpdateDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IScheduleRequestService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/schedule_requests/*"})
@MultipartConfig
public class ScheduleRequestController extends HttpServlet {
    @Inject
    private IScheduleRequestService iScheduleRequestService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        ScheduleRequestUpdateDto dto = Helper.paramsToString(req.getParameterMap()).toModel(ScheduleRequestUpdateDto.class);
        ErrorHandler.handle(resp, () -> this.iScheduleRequestService.UpdateStatus(id, dto.getStatus()));
    }
}
