package service.interfaces;

import dto.ScheduleDto;
import dto.UpdateScheduleStatusDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IScheduleService {
    Message FindAll();

    Message FindAll(String startAt, String arrivalAt,String start,String arrival ,boolean isReturn) throws BadRequestException;

    Message CreateOne(ScheduleDto dto) throws BadRequestException, InternalServerException;

    Message UpdateStatus(UpdateScheduleStatusDto dto) throws InternalServerException;
}
