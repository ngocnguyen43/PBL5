package utils.mapper.implement;

import model.Schedules;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleMapper implements IMapper<Schedules> {
    private final Logger logger = Logger.getLogger(Schedules.class.getName());

    @Override
    public Schedules mapRow(ResultSet result) {
        Schedules schedules = new Schedules();
        try {
            schedules.setScheduleId(result.getString("schedule_id"));
            schedules.setProviderId(result.getString("provider_id"));
            schedules.setTripCode(result.getString("trip_code"));
            schedules.setDeparturePoint(result.getString("departure_id"));
            schedules.setArrivalPoint(result.getString("arrival_id"));
            schedules.setArrivalAt(result.getString("arrival_at"));
            schedules.setStartAt(result.getString("start_at"));
            schedules.setEstimatedTravelTime(result.getFloat("estimated_travel_time"));
            schedules.setSeatCapacity(result.getInt("seat_capacity"));
            schedules.setSeatPrice(result.getBigDecimal("seat_price"));
            schedules.setNotes(result.getString("notes"));
            schedules.setCreatedAt(result.getDate("created_at"));
            schedules.setUpdatedAt(result.getDate("updated_at"));
            schedules.setPhoto(result.getString("photo"));
            return schedules;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
