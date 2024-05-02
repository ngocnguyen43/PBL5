package dao.implement;

import dao.interfaces.ISeatsTicketsDAO;
import model.SeatsTickets;

import java.sql.SQLException;
import java.util.List;

public class SeatsTicketsDAO extends AbstractDAO<SeatsTickets> implements ISeatsTicketsDAO {
    @Override
    public void BulkCreate(List<SeatsTickets> seatsTickets) throws SQLException {
        String sql = "INSERT INTO seats_tickets (seat_id, schedule_id, carriage_id) VALUES (?,?,?)";
        List<Object[]> objects = seatsTickets.stream().map(element -> new Object[]{
                element.getSeatId(),
                element.getScheduleId(),
                element.getCarriageId(),

        }).toList();
        bulkCreate(sql, objects);
    }
}
