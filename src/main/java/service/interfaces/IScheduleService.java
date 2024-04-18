package service.interfaces;

import dto.ScheduleDto;
import utils.exceptions.api.BadRequestException;
import utils.response.Message;

public interface IScheduleService {
    Message FindAll();
    Message CreateOne(ScheduleDto dto) throws BadRequestException;
}
