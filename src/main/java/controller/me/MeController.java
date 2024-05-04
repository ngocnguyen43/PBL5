package controller.me;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import utils.contants.EndPoint;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/me"})
public class MeController extends HttpServlet {
}
