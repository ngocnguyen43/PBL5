package service.interfaces;

import dto.TicketDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

import java.util.List;

public interface ITicketService {
    Message     BulkCreate(List<TicketDto> dto, String userId) throws BadRequestException, InternalServerException;
}
