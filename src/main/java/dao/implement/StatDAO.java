package dao.implement;

import dao.interfaces.IStatDAO;
import model.Stat;
import utils.mapper.implement.SoldTicketsMapper;
import utils.mapper.implement.TotalRevenueMapper;

import java.util.List;

public class StatDAO extends AbstractDAO<Stat> implements IStatDAO {
    @Override
    public Stat GetPercentUsersBuy() {
        String sql = """
                SELECT\s
                    COUNT(CASE WHEN ticket_id IS NOT NULL THEN 1 END) AS sold,
                    COUNT(CASE WHEN ticket_id IS NULL THEN 1 END) AS available
                FROM\s
                    pbl5_1.seats_tickets;""";
        List<Stat> list = query(sql, new SoldTicketsMapper());
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Stat GetTotalRevenue() {
        String sql = "SELECT SUM(total_price) as total, SUM(extra_fee) as revenue from tickets inner join pbl5_1.order on order.order_id = tickets.order_id WHERE order.status = 'Pending';";
        List<Stat> list = query(sql, new TotalRevenueMapper());
        return list.isEmpty() ? null : list.get(0);
    }
}
