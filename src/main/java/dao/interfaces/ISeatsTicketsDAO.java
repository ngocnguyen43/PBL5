package dao.interfaces;

import model.SeatsTickets;

import java.sql.SQLException;
import java.util.List;

public interface ISeatsTicketsDAO extends DAOInterface<SeatsTickets> {
    void BulkCreate(List<SeatsTickets> seatsTickets) throws SQLException;

    List<SeatsTickets> FindAllConflicts(
            String carriageId, String
            scheduleId,
            Integer seatNumber);
}
