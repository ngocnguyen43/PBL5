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

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/carriages"})
@MultipartConfig
public class CarriagesController extends HttpServlet {
    @Inject
    private ICarriageService iCarriageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iCarriageService.FindAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarriageDto dto = Helper.paramsToString(req.getParameterMap()).toModel(CarriageDto.class);
        ErrorHandler.handle(resp, () -> this.iCarriageService.CreateOne(dto));
    }
}
