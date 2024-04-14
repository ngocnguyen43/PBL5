package dao.implement;

import dao.interfaces.ICustomerDAO;
import model.Customer;
import utils.mapper.implement.CustomerMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends AbstractDAO<Customer> implements ICustomerDAO {

    @Override
    public Customer FindOneByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        List<Customer> customers = query(sql, new CustomerMapper(), email);
        return customers.isEmpty() ? null : customers.get(0);
    }

    @Override
    public List<Customer> FindAll() {
        String sql = "SELECT * FROM customer";
        return query(sql, new CustomerMapper());
    }

    public Customer FindOneById(String customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        List<Customer> customers = query(sql, new CustomerMapper(), customerId);
        return customers.isEmpty() ? null : customers.get(0);
    }

    @Override
    public void CreateOne(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (user_id," + "customer_id," + "email," + "phone_number," + "position," + "photo)" + " VALUES(?,?,?,?,?,?)";
        insert(sql,customer.getUserId(),customer.getCustomerId(),customer.getEmail(),customer.getPhoneNumber(),customer.getPosition(),customer.getPhoto());
    }

    public void CreateAll(ArrayList<Customer> arr) throws SQLException {
        for (Customer customer : arr) {
            CreateOne(customer);
        }
    }

    public void DeleteOneById(String customer_id) throws SQLException{
        String sql = "DELETE from customer WHERE customer_id =?";
        delete(sql, customer_id);
    }

    public void DeleteAll(ArrayList<Customer> arr) throws SQLException{
        for (Customer customer : arr) {
            DeleteOneById(customer.getCustomerId());
        }
    }

    public void UpdateOne(Customer customer) throws SQLException{
        String sql = "UPDATE customer "+
                " SET " +
                " full_name=?"+
                ", email=?"+
                ", phone_number=?"+
                ", position=?"+
                " WHERE customer_id=?";
        update(sql, customer.getFullName(), customer.getEmail(), customer.getPhoneNumber(), customer.getPosition(), customer.getCustomerId());
    }
}
