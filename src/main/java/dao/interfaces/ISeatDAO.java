package dao.interfaces;

import model.Seat;
import model.SeatStatus;
import model.TicketInformation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ISeatDAO extends DAOInterface<Seat> {
    void BulkCreate(List<Seat> seats) throws SQLException;

    List<Seat> FindAllByScheduleId(String id, String status);

    List<Seat> FindAllByTrainId(String id);

    List<SeatStatus> FindAllSeatsStatusByCarriageId(String id, String scheduleId);

    BigDecimal FindPrice(List<TicketInformation> ticketInformation);
}
