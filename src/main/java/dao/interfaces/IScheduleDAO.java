package dao.interfaces;

import model.Schedules;

import java.util.List;

public interface IScheduleDAO extends DAOInterface<Schedules> {

    List<Schedules> FindAll();
}
