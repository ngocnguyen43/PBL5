package dao.interfaces;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO extends DAOInterface<Order> {
    void CreateOne(Order order) throws SQLException;

    List<Order> FindAll();

    List<Order> FindAll(String userId);

    List<Order> FindAllForProvider(String userId);

    Boolean FindConfirmId(String orderId );

    Order FindOneByConfirmId(String confirmId);

    void ConfirmOrder(String orderId,List<String> ticketIds);
}
