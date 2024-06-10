package dao.implement;

import dao.interfaces.ISeatDAO;
import model.Seat;
import model.SeatStatus;
import model.TicketInformation;
import utils.mapper.implement.SeatMapper;
import utils.mapper.implement.SeatStatusMapper;
import utils.mapper.implement.TicketPriceMapper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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

    @Override
    public List<Seat> FindAllByTrainId(String id) {
        String sql = """
                SELECT seats.*
                FROM seats
                JOIN carriages ON seats.carriage_id = carriages.carriage_id
                JOIN trains ON carriages.train_id = trains.train_id
                WHERE trains.train_id = ?;""";
        return query(sql, new SeatMapper(), id);
    }

    @Override
    public List<SeatStatus> FindAllSeatsStatusByCarriageId(String id, String scheduleId) {
        String sql = """
                SELECT  seats.seat_number, seats_tickets.status, seats.price
                FROM seats
                INNER JOIN seats_tickets ON seats_tickets.seat_id = seats.seat_id
                WHERE seats.carriage_id = ? AND seats_tickets.schedule_id = ?""";
        return query(sql, new SeatStatusMapper(), id, scheduleId);
    }

    @Override
    public BigDecimal FindPrice(List<TicketInformation> ticketInformation) {
        String[] sqls = new String[ticketInformation.size()];
        Arrays.fill(sqls, """
                SELECT seats.price
                FROM seats
                inner join seats_tickets on seats.seat_id = seats_tickets.seat_id
                WHERE seats_tickets.schedule_id = ?
                AND seats_tickets.carriage_id = ?
                AND seats.seat_number = ?""");
        String combineSqls = String.join(" UNION ALL ", sqls);
        String sql = "select SUM(price) as total FROM ( " + combineSqls + " ) as SEATS_PRICES";
        List<Object[]> objects = ticketInformation.stream().map(element -> new Object[]{
                element.getScheduleId(),
                element.getCarriageId(),
                element.getSeatNumber(),
        }).toList();
        List<BigDecimal> prices = query(sql, new TicketPriceMapper(), objects);
        return prices.isEmpty() ? null : prices.get(0);
    }

    @Override
    public List<SeatStatus> FindBySeatNumberCarriageIdAndScheduleId(List<Integer> seatNumbers, String carriageId, String scheduleId) {
        StringJoiner joiner = new StringJoiner(", ", "IN (", ")");
        for (Integer value : seatNumbers) {
            joiner.add(value.toString());
        }
        String sql = """
                SELECT  seats.seat_number, seats_tickets.status
                FROM seats
                INNER JOIN seats_tickets ON seats_tickets.seat_id = seats.seat_id
                WHERE seats.carriage_id = ? AND seats_tickets.schedule_id = ? AND status = 'Booked' AND seats.seat_number """ + joiner;
        return query(sql, new SeatStatusMapper(), carriageId, scheduleId);
    }
}
