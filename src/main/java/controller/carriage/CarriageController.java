package controller.carriage;

import dto.CarriageDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ICarriageService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/carriages/*"})
@MultipartConfig
public class CarriageController extends HttpServlet {
    @Inject
    private ICarriageService iCarriageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String scheduleId = req.getParameter("schedule");
        ErrorHandler.handle(resp, () -> this.iCarriageService.FindOne(id, scheduleId));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        CarriageDto dto = Helper.paramsToString(req.getParameterMap()).toModel(CarriageDto.class);
        ErrorHandler.handle(resp, () -> this.iCarriageService.UpdateOne(dto, id));
    }
}
