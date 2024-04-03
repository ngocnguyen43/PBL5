package org.example.ticketbox.Controller;

import org.example.ticketbox.Service.IInjectService;
import org.example.ticketbox.utils.errorHandler.ErrorHandler;
import org.example.ticketbox.utils.exceptions.api.InvalidEndpointException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/test/1")
public class Login extends HttpServlet {
    @Inject
    private IInjectService iInjectService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp,new InvalidEndpointException());
    }
}
