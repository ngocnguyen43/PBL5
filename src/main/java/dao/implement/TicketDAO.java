package dao.implement;

import dao.interfaces.ITicketDAO;
import model.Ticket;

import java.sql.SQLException;

public class TicketDAO extends AbstractDAO<Ticket> implements ITicketDAO {
    @Override
    public void CreateOne(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (ticket_id," +
                "order_id," +
                "price," +
                "photo," +
                "user_id," +
                "extra_fee,total_price) VALUES  (?,?,?,?,?,?,?) ";
        insert(sql, ticket.getTicketId(), ticket.getOrderId(), ticket.getPrice(), ticket.getPhoto(), ticket.getUserId(), ticket.getExtraFee(), ticket.getTotalPrice());
    }
}
