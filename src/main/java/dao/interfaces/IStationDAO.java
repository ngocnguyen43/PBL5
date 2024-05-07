package dao.interfaces;

import model.Station;

import java.sql.SQLException;
import java.util.List;

public interface IStationDAO extends DAOInterface<Station> {
    List<Station> FindAll();

    List<Station> FindAllByName(String name);

    Station FindOneById(String id);

    void CreateOne(Station station) throws SQLException;
}
