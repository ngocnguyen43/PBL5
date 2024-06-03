package service.interfaces;

import dto.CustomerDto;
import utils.exceptions.api.DatabaseOperationException;
import utils.response.Message;

public interface ICustomerService {
    Message FindAllCustomers();

    Message FindOneByID(String customerId);

    Message FindOneByUserID(String userId);

    Message UpdateCustomer(CustomerDto customerDto) throws DatabaseOperationException;

    Message DeleteCustomer(CustomerDto customerDto) throws DatabaseOperationException;
}
