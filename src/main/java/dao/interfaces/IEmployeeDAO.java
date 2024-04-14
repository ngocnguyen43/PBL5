package dao.interfaces;

import model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IEmployeeDAO {
    Employee FindOneByEmail(String email);

    List<Employee> FindAll();

    Employee FindOneById(String employeeId);

    void CreateOne(Employee employee) throws SQLException;

    void CreateAll(ArrayList<Employee> arr) throws SQLException;

    void DeleteOneById(String employee_id) throws SQLException;

    void DeleteAll(ArrayList<Employee> arr) throws SQLException;

    void UpdateOne(Employee employee) throws SQLException;

}
