package dao.implement;

import dao.interfaces.ISeatDAO;
import model.Seat;
import utils.mapper.implement.SeatMapper;

import java.sql.SQLException;
import java.util.List;

public class SeatDAO extends AbstractDAO<Seat> implements ISeatDAO {
    @Override
    public void BulkCreate(List<Seat> seats) throws SQLException {
        String sql = "INSERT INTO seats (seat_id, carriage_id, seat_number,price) VALUES (?,?,?,?)";
        List<Object[]> objects = seats.stream().map(element -> new Object[]{
                element.getSeatId(),
                element.getCarriageId(),
                element.getSeatNumber(),
                element.getPrice()
        }).toList();
        bulkCreate(sql, objects);
    }

    @Override
    public List<Seat> FindAllByScheduleId(String id, String status) {
        String sql = "SELECT seats.*\n" +
                "FROM seats\n" +
                "JOIN carriages ON seats.carriage_id = carriages.carriage_id\n" +
                "JOIN trains ON carriages.train_id = trains.train_id\n" +
                "JOIN schedules ON trains.train_id = schedules.train_id\n" +
                "JOIN schedule_requests ON schedule_requests.schedule_id = schedules.schedule_id\n" +
                "WHERE schedules.schedule_id = ? AND schedule_requests.status = ?;";
        return query(sql, new SeatMapper(), id, status);
    }
}
