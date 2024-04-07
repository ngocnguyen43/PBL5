package service.impl;

import dao.interfaces.ICustomerDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import service.interfaces.ICustomerService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;

public class CustomerService implements ICustomerService {
    @Inject
    private ICustomerDAO iCustomerDAO;

    @Override
    public Message FindAllCustomers() {
        List<Customer> customers = iCustomerDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();

        Data data = new Data.Builder(null).withResults(customers).build();
        return new Message.Builder(meta).withData(data).build();

    }
}
