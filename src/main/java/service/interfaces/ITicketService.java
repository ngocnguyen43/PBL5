package service.interfaces;

import dto.TicketDto;
import utils.response.Message;

import java.util.List;

public interface ITicketService {
    Message BulkCreate(List<TicketDto> dto);
}
