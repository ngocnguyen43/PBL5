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

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.CUSTOMER})
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

//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String customerId = req.getRequestURI().split("/")[2]; // Lấy ID khách hàng từ URL
//        // Xử lý dữ liệu khách hàng ở đây
//        CustomerDto customerDto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
//        // Set ID cho khách hàng
//        customerDto.setCustomerId(customerId);
//        ErrorHandler.handle(resp, () -> this.iCustomerService.UpdateCustomer(customerDto));
//    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
        ErrorHandler.handle(resp, () -> this.iCustomerService.UpdateCustomer(customerDto));
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = Helper.paramsToString(req.getParameterMap()).toModel(CustomerDto.class);
        ErrorHandler.handle(resp, () -> this.iCustomerService.DeleteCustomer(customerDto));
    }

}
