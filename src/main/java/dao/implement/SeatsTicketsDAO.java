package dao.implement;

import dao.interfaces.ISeatsTicketsDAO;
import model.SeatsTickets;
import utils.mapper.implement.SeatsTicketsMapper;

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

    @Override
    public List<SeatsTickets> FindAllConflicts(String scheduleId, String carriageId, Integer seatId) {
        String sql = "SELECT seats_tickets.* FROM seats_tickets JOIN seats ON seats.carriage_id = seats_tickets.carriage_id WHERE seats_tickets.carriage_id = ? AND schedule_id = ? AND seats.seat_number = ? AND seats_tickets.status = 'Pending';";
        return query(sql, new SeatsTicketsMapper(), carriageId, scheduleId, seatId);

    }
}
