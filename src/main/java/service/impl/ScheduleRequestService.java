package service.impl;

import dao.interfaces.IScheduleRequestDAO;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.ScheduleRequest;
import service.interfaces.IScheduleRequestService;
import utils.exceptions.server.InternalServerException;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleRequestService implements IScheduleRequestService {
    private final Logger logger = Logger.getLogger(ScheduleRequestService.class.getName());
    @Inject
    private IScheduleRequestDAO iScheduleRequestDAO;

    @Override
    public Message FindAll() {
        try {
            List<ScheduleRequest> scheduleRequests = this.iScheduleRequestDAO.FindAll();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            Data data = new Data.Builder(null).withResults(scheduleRequests).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    @Override
    public Message UpdateStatus(String id, String status) throws InternalServerException {
        try {
            iScheduleRequestDAO.UpdateStatus(id, status);
            ScheduleRequest scheduleRequest = this.iScheduleRequestDAO.FindOneById(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            Data data = new Data.Builder(null).withResults(scheduleRequest).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }
}
