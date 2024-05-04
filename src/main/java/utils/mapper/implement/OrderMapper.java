package utils.mapper.implement;

import model.Order;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;

public class OrderMapper implements IMapper<Order> {
    @Override
    public Order mapRow(ResultSet result) {
        return null;
    }
}
