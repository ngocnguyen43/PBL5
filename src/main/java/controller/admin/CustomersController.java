package controller.admin;

import dto.CustomerDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ICustomerService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + "/customers"})
@MultipartConfig

public class CustomersController extends HttpServlet {
    @Inject
    private ICustomerService iCustomerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iCustomerService.FindAllCustomers());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
        ErrorHandler.handle(resp, () -> this.iCustomerService.FindOneByID(customerDto.getCustomerId()));
    }


}
