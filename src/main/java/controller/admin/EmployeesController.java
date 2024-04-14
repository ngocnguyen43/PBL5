package controller.admin;

import dto.CustomerDto;
import dto.EmployeeDto;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.interfaces.ICustomerService;
import service.interfaces.IEmployeeService;
import utils.contants.EndPoint;
import utils.errorHandler.ErrorHandler;
import utils.helper.Helper;

import java.io.IOException;

@WebServlet(urlPatterns = {EndPoint.API + EndPoint.VERSION + EndPoint.EMPLOYEE})
@MultipartConfig

public class EmployeesController extends HttpServlet {
    @Inject
    private IEmployeeService iEmployeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ErrorHandler.handle(resp, () -> this.iEmployeeService.FindAllEmployee());
    }

    //DO POST -> FindOneByID
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        EmployeeDto employeeDto = Helper.paramsToString(req.getParameterMap()).toModel(EmployeeDto.class);
//        ErrorHandler.handle(resp, () -> this.iEmployeeService.FindOneByID(employeeDto.getEmployeeId()));
//    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDto employeeDto = Helper.paramsToString(req.getParameterMap()).toModel(EmployeeDto.class);
        System.out.printf(employeeDto.toString());
        ErrorHandler.handle(resp, () -> this.iEmployeeService.InsertEmployee(employeeDto));
        System.out.println("ok");
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
        EmployeeDto employeeDto = Helper.paramsToString(req.getParameterMap()).toModel(EmployeeDto.class);
        ErrorHandler.handle(resp, () -> this.iEmployeeService.UpdateEmployee(employeeDto));
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeDto employeeDto = Helper.paramsToString(req.getParameterMap()).toModel(EmployeeDto.class);
        ErrorHandler.handle(resp, () -> this.iEmployeeService.DeleteEmployee(employeeDto));
    }

}
