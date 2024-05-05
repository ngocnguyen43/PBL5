package dao.interfaces;

import model.Order;
import model.Ticket;
import model.TicketInformation;

import java.sql.SQLException;
import java.util.List;

public interface ITicketDAO extends DAOInterface<Ticket> {
    void CreateOne(Ticket ticket) throws SQLException;

    void BulkCreate(List<Ticket> tickets, List<TicketInformation> ticketInformation, Order order) throws SQLException;
}
