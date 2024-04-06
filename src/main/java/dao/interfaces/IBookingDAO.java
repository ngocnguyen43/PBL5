package dao.interfaces;


import model.Booking;

import java.util.List;

public interface IBookingDAO extends DAOInterface<Booking> {
    Booking FindOne(String id);

    List<Booking> FindAll();
}
