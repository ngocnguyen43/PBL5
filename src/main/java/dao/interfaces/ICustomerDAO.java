package dao.interfaces;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ICustomerDAO extends DAOInterface<Customer> {
    Customer FindOneByEmail(String email);
    List<Customer> FindAll();
    Customer FindOneById(String customerId);
    Customer FindOneByUserId(String userId);
    void CreateOne(Customer customer) throws SQLException;

    void CreateAll(ArrayList<Customer> arr) throws SQLException;

    void DeleteOneById(String customer_id) throws SQLException;

    void DeleteAll(ArrayList<Customer> arr) throws SQLException;

    void UpdateOne(Customer customer) throws SQLException;

}
