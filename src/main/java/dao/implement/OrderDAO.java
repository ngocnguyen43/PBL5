package dao.implement;

import dao.interfaces.IOrderDAO;
import model.Order;
import utils.mapper.implement.OrderMapper;

import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {
    @Override
    public void CreateOne(Order order) throws SQLException {
        String sql = "INSERT INTO order (order_id, user_id, order_date) VALUES (?,?,?)";
        insert(sql, order.getOrderId(), order.getUserId(), order.getOrderDate());
    }

    @Override
    public List<Order> FindAll() {
        String sql = "SELECT * FROM order";
        return query(sql, new OrderMapper());
    }
}
