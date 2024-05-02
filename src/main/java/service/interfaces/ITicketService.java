package service.interfaces;

import dto.TicketDto;
import utils.response.Message;

public interface ITicketService {
    Message CreateOne(TicketDto dto);
}
