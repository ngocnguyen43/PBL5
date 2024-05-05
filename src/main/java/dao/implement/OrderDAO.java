package dao.implement;

import dao.interfaces.IOrderDAO;
import model.Order;
import utils.mapper.implement.OrderMapper;

import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {
    @Override
    public void CreateOne(Order order) throws SQLException {
        String sql = "INSERT INTO pbl5_1.order (order_id, user_id, order_date) VALUES (?,?,?)";
        insert(sql, order.getOrderId(), order.getUserId(), order.getOrderDate());
    }

    @Override
    public List<Order> FindAll() {
        String sql = "SELECT * FROM pbl5_1.order ORDER BY order_date";
        return query(sql, new OrderMapper());
    }

    @Override
    public List<Order> FindAll(String userId) {
        String sql = "SELECT * FROM pbl5_1.order WHERE user_id = ? ORDER BY order_date";
        return query(sql, new OrderMapper(), userId);
    }

    @Override
    public List<Order> FindAllForProvider(String userId) {
        String sql = """
                SELECT distinct pbl5_1.order.*\s
                                FROM pbl5_1.order
                                 JOIN pbl5_1.tickets ON pbl5_1.tickets.order_id = pbl5_1.order.order_id
                                 JOIN pbl5_1.seats_tickets ON pbl5_1.seats_tickets.ticket_id= pbl5_1.tickets.ticket_id
                                 JOIN pbl5_1.schedules ON pbl5_1.schedules.schedule_id = pbl5_1.seats_tickets.schedule_id
                                WHERE pbl5_1.schedules.user_id = ? ;""";
        return query(sql, new OrderMapper(), userId);
    }
}
