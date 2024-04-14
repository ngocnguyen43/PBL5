package utils.mapper.implement;

import model.Customer;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerMapper implements IMapper<Customer> {
    Logger logger = Logger.getLogger(CustomerMapper.class.getName());

    @Override
    public Customer mapRow(ResultSet result) {
        Customer customer = new Customer();
        try {
            customer.setCustomerId(result.getString("customer_id"));
            customer.setUserId(result.getString("user_id"));
            customer.setFullName(result.getString("full_name"));
            customer.setEmail(result.getString("email"));
            customer.setPhoneNumber(result.getString("phone_number"));
            customer.setPosition(result.getString("position"));
            customer.setPhoto(result.getString("photo"));
            return customer;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
