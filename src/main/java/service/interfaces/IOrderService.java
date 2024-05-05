package service.interfaces;

import model.User;
import utils.response.Message;

public interface IOrderService {
    Message FindAllOrders(User user);

    Message FindAllOrdersByUserId();

}
