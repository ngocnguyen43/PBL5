package utils.mapper.implement;

import model.Employee;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeMapper implements IMapper<Employee> {
    Logger logger = Logger.getLogger(EmployeeMapper.class.getName());

    @Override
    public Employee mapRow(ResultSet result) {
        Employee employee = new Employee();
        try {
            employee.setEmployeeId(result.getString("employee_id"));
            employee.setUserId(result.getString("user_id"));
            employee.setFullName(result.getString("full_name"));
            employee.setGender(result.getString("gender"));
            employee.setEmail(result.getString("email"));
            employee.setPhoneNumber(result.getString("phone_number"));
            employee.setPosition(result.getString("position"));
            employee.setPhoto(result.getString("photo"));
            return employee;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
