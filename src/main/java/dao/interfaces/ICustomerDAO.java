package dao.interfaces;

import model.Booking;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO extends DAOInterface<Customer>  {
    Customer FindOneByEmail(String email);

    List<Customer> FindAll();

    void  CreateOne(Customer customer) throws SQLException;
}
