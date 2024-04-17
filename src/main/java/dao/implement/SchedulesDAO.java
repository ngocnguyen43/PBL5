package dao.implement;

import dao.interfaces.IScheduleDAO;
import model.Schedules;
import utils.mapper.implement.ScheduleMapper;

import java.util.List;

public class SchedulesDAO extends AbstractDAO<Schedules> implements IScheduleDAO {
    @Override
    public List<Schedules> FindAll() {
        String sql = "SELECT * FROM schedules";
        return query(sql, new ScheduleMapper());
    }
}
