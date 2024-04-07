package dao.implement;

import dao.interfaces.ICustomerDAO;
import model.Customer;
import utils.mapper.implement.CustomerMapper;

import java.sql.SQLException;
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

    @Override
    public void CreateOne(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (user_id," + "customer_id," + "email," + "phone_number," + "position," + "photo)" + " VALUES(?,?,?,?,?,?)";
        insert(sql,customer.getUserId(),customer.getCustomerId(),customer.getEmail(),customer.getPhoneNumber(),customer.getPosition(),customer.getPhoto());
    }
}
