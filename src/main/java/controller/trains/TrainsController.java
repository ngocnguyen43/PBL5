package controller.trains;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.TrainDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ITrainService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/trains"})
@MultipartConfig
public class TrainsController extends HttpServlet {
    @Inject
    private ITrainService iTrainService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iTrainService.FindAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TrainDto dto = Helper.paramsToString(req.getParameterMap()).toModel(TrainDto.class);
        ErrorHandler.handle(resp, () -> this.iTrainService.CreateOne(dto));
    }
}
