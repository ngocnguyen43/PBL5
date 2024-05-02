package dao.interfaces;

import model.Ticket;

import java.sql.SQLException;

public interface ITicketDAO extends DAOInterface<Ticket> {
    void CreateOne(Ticket ticket) throws SQLException;
}
