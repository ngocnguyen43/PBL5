package service.impl;

import dao.interfaces.IScheduleDAO;
import dto.ScheduleDto;
import dto.UpdateScheduleStatusDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Schedules;
import service.interfaces.IScheduleService;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleService implements IScheduleService {
    @Inject
    private IScheduleDAO iScheduleDAO;

    private final Logger logger = Logger.getLogger(ScheduleService.class.getName());

    @Override
    public Message FindAll() {
        List<Schedules> schedules = this.iScheduleDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedules).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message CreateOne(ScheduleDto dto) throws InternalServerException {
        Schedules model = Helper.objectMapper(dto, Schedules.class);
        String id = IDGenerator.generate(10);
        String tripCode = IDGenerator.generate(10);
        model.setScheduleId(id);
        model.setTripCode(tripCode);
        try {
            this.iScheduleDAO.CreateOne(model);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }

    @Override
    public Message UpdateStatus(UpdateScheduleStatusDto dto) throws InternalServerException {
        try {
            this.iScheduleDAO.UpdateStatus(dto.getId(), dto.getStatus());
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }
}
