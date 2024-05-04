package dao.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Seat;
import model.SeatStatus;
import model.TicketInformation;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface ISeatDAO extends DAOInterface<Seat> {
    void BulkCreate(List<Seat> seats) throws SQLException;

    List<Seat> FindAllByScheduleId(String id, String status);
    List<SeatStatus> FindAllSeatsStatusByCarriageId(String id);
    BigDecimal FindPrice(List<TicketInformation> ticketInformation);
}
