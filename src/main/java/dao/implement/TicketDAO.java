package dao.implement;

import dao.interfaces.ITicketDAO;
import model.Order;
import model.Ticket;
import model.TicketInformation;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public void BulkCreate(List<Ticket> tickets, List<TicketInformation> ticketInformation, Order order) throws SQLException {
        assert !tickets.isEmpty() && !ticketInformation.isEmpty();
        String ticketsSql = "INSERT INTO tickets (ticket_id," +
                "order_id," +
                "price," +
                "photo," +
                "user_id," +
                "extra_fee,total_price) VALUES  (?,?,?,?,?,?,?) ";
        String seatTicketSql = """
                UPDATE t
                SET t.ticket_id = ?, t.status = 'Pending'
                FROM seats_tickets t INNER JOIN seats ON seats.seat_id = t.seat_id
                WHERE carriage_id = ? AND schedule_id = ?
                """;
        String orderSql = "INSERT INTO orders (order_id,user_id,order_date,confirm_url_id) VALUES (?,?,?,?)";
        List<Object[]> listTickets = tickets.stream().map(e ->
                new Object[]{
                        e.getOrderId(),
                        e.getPrice(),
                        e.getPhoto(),
                        e.getUserId(),
                        e.getPrice(),
                        e.getExtraFee(),
                        e.getTotalPrice()

                }).toList();

        List<Object[]> listSeatTickets = ticketInformation.stream().map(e -> new Object[]{
                e.getTicketId(),
                e.getCarriageId(),
                e.getScheduleId()
        }).toList();
        List<Object[]> orderObject = Arrays.asList(new Object[][]{
                {order.getOrderId(),
                        order.getUserId(),
                        order.getOrderDate(),
                        order.getConfirmUrlId()}
        });

        bulkCreate(Arrays.asList(ticketsSql, seatTicketSql, orderSql), Arrays.asList(listTickets, listSeatTickets, orderObject));
    }
}
