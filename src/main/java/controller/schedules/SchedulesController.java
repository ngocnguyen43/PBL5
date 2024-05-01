package controller.schedules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ScheduleDto;
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

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/schedules"})
@MultipartConfig
public class SchedulesController extends HttpServlet {
    @Inject
    private IScheduleService iScheduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iScheduleService.FindAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleDto dto = Helper.paramsToString(req.getParameterMap()).toModel(ScheduleDto.class);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dto));
        ErrorHandler.handle(resp, () -> this.iScheduleService.CreateOne(dto));
    }
}
