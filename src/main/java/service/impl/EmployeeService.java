package service.impl;

import dao.interfaces.IEmployeeDAO;
import dao.interfaces.IUserDAO;
import dto.EmployeeDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.User;
import service.interfaces.IEmployeeService;
import utils.exceptions.api.DatabaseOperationException;
import utils.exceptions.api.RegistrationFailedException;
import utils.helper.HashPassword;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeService implements IEmployeeService {
    @Inject
    private IEmployeeDAO iEmployeeDAO;
    @Inject
    private IUserDAO iUserDAO;
    private final Logger logger = Logger.getLogger(AuthService.class.getName());

    @Override
    public Message FindAllEmployee() {
        List<Employee> employees = iEmployeeDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(employees).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message FindOneByID(String employeeId){
        Employee employee = iEmployeeDAO.FindOneById(employeeId);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(employee).build();
        return new Message.Builder(meta).withData(data).build();

    }
    @Override
    public Message InsertEmployee(EmployeeDto employeeDto) throws RegistrationFailedException {
        if(employeeDto.getEmail()==null){
            throw new RegistrationFailedException();
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(employeeDto.getEmail());
        if (!matcher.matches()) {
            throw new RegistrationFailedException();
        }

        boolean isExist = this.iEmployeeDAO.FindOneByEmail(employeeDto.getEmail()) != null;
        if (isExist) {
            throw new RegistrationFailedException();
        }

        String userId = IDGenerator.generate(10);
        String employeeId = IDGenerator.generate(10);

        Employee employee = Helper.objectMapper(employeeDto, Employee.class);
        User user = Helper.objectMapper(employeeDto, User.class);

        employee.setEmployeeId(employeeId);
        employee.setUserId(userId);
        user.setUserId(userId);
        user.setPassword(HashPassword.Hash(user.getPassword()));
        user.setRoleId("3");

        System.out.printf(employee.toString());
        System.out.printf(user.toString());
        try {
            this.iUserDAO.CreateOne(user);
            this.iEmployeeDAO.CreateOne(employee);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new RegistrationFailedException();
        }

    }
    @Override
    public Message UpdateEmployee(EmployeeDto employeeDto) throws DatabaseOperationException{
        Employee employee = Helper.objectMapper(employeeDto, Employee.class);
        try {
            iEmployeeDAO.UpdateOne(employee);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }
    public Message DeleteEmployee(EmployeeDto employeeDto) throws DatabaseOperationException{
        Employee employee = Helper.objectMapper(employeeDto, Employee.class);
        try {
            Employee employee1 = iEmployeeDAO.FindOneById(employee.getEmployeeId());
            String userId = employee1.getUserId();
            iEmployeeDAO.DeleteOneById(employee.getEmployeeId());
            iUserDAO.DeleteOneById(userId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }
}
