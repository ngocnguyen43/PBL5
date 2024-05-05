package utils.mapper.implement;

import model.Order;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper implements IMapper<Order> {
    private final Logger logger = Logger.getLogger(OrderMapper.class.getName());

    @Override
    public Order mapRow(ResultSet result) {
        Order order = new Order();
        try {
            order.setOrderId(result.getString("order_id"));
            order.setUserId(result.getString("user_id"));
            order.setOrderDate(result.getString("order_date"));
            order.setStatus(result.getString("status"));
            order.setPaidDate(result.getString("order_date"));
            order.setConfirmUrlId(result.getString("confirm_url_id"));
            return order;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
