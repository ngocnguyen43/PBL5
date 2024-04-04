package org.example.ticketbox.Controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ticketbox.Service.IInjectService;
import org.example.ticketbox.utils.errorHandler.ErrorHandler;
import org.example.ticketbox.utils.exceptions.api.InvalidEndpointException;


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
