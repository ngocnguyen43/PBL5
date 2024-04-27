package controller.admin;

import dto.UpdateScheduleStatusDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.IScheduleService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.ADMIN + "/schedule"})
@MultipartConfig
public class ScheduleAdminController extends HttpServlet {

    @Inject
    private IScheduleService iScheduleService;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdateScheduleStatusDto dto = Helper.paramsToString(req.getParameterMap()).toModel(UpdateScheduleStatusDto.class);
        ErrorHandler.handle(resp, () -> this.iScheduleService.UpdateStatus(dto));
    }
}
