package controller.schedules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ScheduleDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.contants.EndPoint;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/schedule"})
@MultipartConfig
public class ScheduleController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScheduleDto dto = Helper.paramsToString(req.getParameterMap()).toModel(ScheduleDto.class);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dto));
    }
}
