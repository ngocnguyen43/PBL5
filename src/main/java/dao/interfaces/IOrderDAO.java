package dao.interfaces;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO extends DAOInterface<Order> {
    void CreateOne(Order order) throws SQLException;
    List<Order> FindAll();
}