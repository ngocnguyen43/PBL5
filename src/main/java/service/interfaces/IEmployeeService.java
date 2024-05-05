package service.interfaces;

import dto.EmployeeDto;
import utils.exceptions.api.DatabaseOperationException;
import utils.exceptions.api.RegistrationFailedException;
import utils.response.Message;

public interface IEmployeeService {
    Message FindAllEmployee();

    Message FindOneByID(String customerId);

    Message InsertEmployee(EmployeeDto employeeDto) throws RegistrationFailedException;

    Message UpdateEmployee(EmployeeDto employeeDto) throws DatabaseOperationException;

    Message DeleteEmployee(EmployeeDto employeeDto) throws DatabaseOperationException;
}
