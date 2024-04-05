package main.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.service.IInjectService;
import main.utils.errorHandler.ErrorHandler;
import main.utils.exceptions.api.InvalidEndpointException;


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
