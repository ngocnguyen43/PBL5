package service.impl;

import dao.interfaces.IBookingDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Booking;
import service.interfaces.IBookingService;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;

public class BookingService implements IBookingService {
    @Inject
    private IBookingDAO iBookingDAO;

    @Override
    public Message GetOneById(String id) {
        Booking booking = iBookingDAO.FindOne(id);
        Data data = new Data.Builder(null).withResults(booking).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();


    }

    @Override
    public Message GetAll() {
        List<Booking> bookings = iBookingDAO.FindAll();
        Data data = new Data.Builder(null).withResults(bookings).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
