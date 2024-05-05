package dao.implement;

import dao.interfaces.IEmployeeDAO;
import model.Employee;
import utils.mapper.implement.EmployeeMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends AbstractDAO<Employee> implements IEmployeeDAO {

    @Override
    public Employee FindOneByEmail(String email) {
        String sql = "SELECT * FROM employees WHERE email = ?";
        List<Employee> employees = query(sql, new EmployeeMapper(), email);
        return employees.isEmpty() ? null : employees.get(0);
    }

    @Override
    public List<Employee> FindAll() {
        String sql = "SELECT * FROM employees";
        return query(sql, new EmployeeMapper());
    }

    @Override
    public Employee FindOneById(String employeeId) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        List<Employee> employees = query(sql, new EmployeeMapper(), employeeId);
        return employees.isEmpty() ? null : employees.get(0);
    }

    @Override
    public void CreateOne(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (user_id," + "employee_id," + "full_name," + "email," + "gender," + "phone_number," + "position," + "photo)" + " VALUES(?,?,?,?,?,?,?,?)";
        insert(sql, employee.getUserId(), employee.getEmployeeId(), employee.getFullName(), employee.getEmail(), employee.getGender(), employee.getPhoneNumber(), employee.getPosition(), employee.getPhoto());
    }

    public void CreateAll(ArrayList<Employee> arr) throws SQLException {
        for (Employee employee : arr) {
            CreateOne(employee);
        }
    }

    public void DeleteOneById(String employee_id) throws SQLException {
        String sql = "DELETE from employees WHERE employee_id =?";
        delete(sql, employee_id);
    }

    public void DeleteAll(ArrayList<Employee> arr) throws SQLException {
        for (Employee employee : arr) {
            DeleteOneById(employee.getEmployeeId());
        }
    }

    public void UpdateOne(Employee employee) throws SQLException {
        String sql = "UPDATE employees " +
                " SET " +
                " full_name=?" +
                ", email=?" +
                ", gender=?" +
                ", phone_number=?" +
                ", position=?" +
                " WHERE employee_id=?";
        update(sql, employee.getFullName(), employee.getEmail(), employee.getGender(), employee.getPhoneNumber(), employee.getPosition(), employee.getEmployeeId());
    }
}
