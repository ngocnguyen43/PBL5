package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.ICustomerDAO;
import dao.interfaces.IUserDAO;
import dto.CustomerDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.User;
import service.interfaces.ICustomerService;
import utils.exceptions.api.DatabaseOperationException;
import utils.helper.Helper;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService implements ICustomerService {
    private final Logger logger = Logger.getLogger(AuthService.class.getName());
    @Inject
    private ICustomerDAO iCustomerDAO;
    @Inject
    private IUserDAO iUserDAO;

    @Override
    public Message FindAllCustomers() {
        List<User> customers = iCustomerDAO.FindAllCustomer();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(customers).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message FindOneByID(String customerId) {
        Customer customers = iCustomerDAO.FindOneById(customerId);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(customers).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message FindOneByUserID(String userId) {
        Customer customers = iCustomerDAO.FindOneByUserId(userId);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(customers).build();
        return new Message.Builder(meta).withData(data).build();

    }

    @Override
    public Message UpdateCustomer(CustomerDto customerDto) throws DatabaseOperationException {
        Customer customer = Helper.objectMapper(customerDto, Customer.class);
        try {
            iCustomerDAO.UpdateOne(customer);
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(customer));
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public Message DeleteCustomer(CustomerDto customerDto) throws DatabaseOperationException {
        Customer customer = Helper.objectMapper(customerDto, Customer.class);
        try {
            Customer customer1 = iCustomerDAO.FindOneById(customer.getCustomerId());
            String userId = customer1.getUserId();
            iCustomerDAO.DeleteOneById(customer.getCustomerId());
            iUserDAO.DeleteOneById(userId);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new DatabaseOperationException(e.getMessage());
        }
    }
}
