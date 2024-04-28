package utils.mapper.implement;

import model.Schedules;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleMapper implements IMapper<Schedules> {
    private final Logger logger = Logger.getLogger(Schedules.class.getName());
    private Boolean shouldShowDate;

    public ScheduleMapper(Boolean shouldShowDate) {
        this.shouldShowDate = shouldShowDate;
    }

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
            schedules.setNotes(result.getString("notes"));
            if (shouldShowDate) {
                schedules.setCreatedAt(result.getString("created_at"));
                schedules.setUpdatedAt(result.getString("updated_at"));
            } else {
                schedules.setCreatedAt(null);
                schedules.setUpdatedAt(null);
            }
            schedules.setPhoto(result.getString("photo"));
            return schedules;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
