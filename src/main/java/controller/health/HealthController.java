package controller.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.contants.EndPoint;

import java.io.IOException;

@WebServlet(urlPatterns = {"/health"})
public class HealthController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(new ObjectMapper().writeValueAsString("OK"));
        resp.getWriter().flush();
    }
}
