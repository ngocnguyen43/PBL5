package dao.implement;

import dao.interfaces.IOrderDAO;
import model.Order;
import utils.mapper.implement.ConfirmURLMapper;
import utils.mapper.implement.OrderMapper;

import java.sql.SQLException;
import java.util.Arrays;
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

    @Override
    public Boolean FindConfirmId(String orderId) {
        String sql = "SELECT confirm_url_id FROM pbl5_1.order WHERE confirm_url_id = ?";
        List<String> ids = query(sql, new ConfirmURLMapper(), orderId);
        return !ids.isEmpty();
    }

    @Override
    public Order FindOneByConfirmId(String confirmId) {
        String sql = "SELECT * FROM pbl5_1.order WHERE confirm_url_id = ?";
        List<Order> orders = query(sql, new OrderMapper(), confirmId);
        return orders.isEmpty() ? null : orders.get(0);
    }

    @Override
    public void ConfirmOrder(String orderId) throws SQLException {
        final String unix = "" + System.currentTimeMillis() / 1000L;

        String orderSql = "update pbl5_1.order set order.status = 'Confirmed', paid_date = ? where order_id = ?";
        String ticketSql = """
                update seats_tickets join tickets on tickets.ticket_id = seats_tickets.ticket_id\s
                join pbl5_1.order on tickets.order_id = order.order_id
                set seats_tickets.status= 'Booked' where order.order_id = ?;""";
        List<List<Object[]>> params = List.of(Arrays.asList(
                (new Object[][]{new Object[]{unix, orderId}})
        ), Arrays.asList(
                (new Object[][]{new Object[]{orderId}})
        ));
        bulkCreate(Arrays.asList(orderSql, ticketSql), params);
    }
}
