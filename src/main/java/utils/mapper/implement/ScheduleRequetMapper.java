package utils.mapper.implement;

import model.ScheduleRequest;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleRequetMapper implements IMapper<ScheduleRequest> {

    private final Logger logger = Logger.getLogger(ScheduleRequetMapper.class.getName());

    @Override
    public ScheduleRequest mapRow(ResultSet result) {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        try {

            scheduleRequest.setRequestId(result.getString("request_id"));
            scheduleRequest.setScheduleId(result.getString("schedule_id"));
            scheduleRequest.setStatus(result.getString("status"));
            scheduleRequest.setType(result.getString("type"));
            scheduleRequest.setCreatedAt(result.getString("created_at"));
            scheduleRequest.setUpdatedAt(result.getString("updated_at"));

            return scheduleRequest;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
