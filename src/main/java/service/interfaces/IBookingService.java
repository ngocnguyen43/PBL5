package service.interfaces;

import utils.exceptions.api.BadRequestException;
import utils.response.Message;

public interface IBookingService {
    Message GetOneById(String id) throws BadRequestException;

    Message GetAll();
}
