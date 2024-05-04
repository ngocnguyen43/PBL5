package service.interfaces;

import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IScheduleRequestService {
    Message FindAll();

    Message UpdateStatus(String id,String status) throws InternalServerException;
}
