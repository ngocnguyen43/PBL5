package service.interfaces;

import model.User;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IOrderService {
    Message FindAllOrders(User user);

    Message FindAllOrdersByUserId();

    Message ConfirmOrder(String confirmId) throws InternalServerException;
}
