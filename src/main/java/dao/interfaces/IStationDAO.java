package dao.interfaces;

import model.Station;

import java.util.List;

public interface IStationDAO extends DAOInterface<Station> {
    List<Station> FindAll();

    List<Station> FindAllByName(String name);
}
