package dao.implement;

import dao.interfaces.IRevenueDAO;
import model.Revenue;
import utils.mapper.RevenueMapper;

import java.util.List;

public class RevenueDAO extends AbstractDAO<Revenue> implements IRevenueDAO {

    @Override
    public List<Revenue> FindAll() {
        String sql = "SELECT SUM(tickets.total_price) as revenue, pbl5_1.order.order_date from pbl5_1.order join pbl5_1.tickets on tickets.order_id = order.order_id GROUP by tickets.order_id  order by order_date desc;\n";
        return query(sql, new RevenueMapper());
    }
}
