package service.interfaces;

import utils.response.Message;

public interface IBookingService {
    Message GetOneById(String id);
    Message GetAll();
}
