package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.IScheduleDAO;
import dao.interfaces.IScheduleRequestDAO;
import dto.ScheduleDto;
import dto.UpdateScheduleStatusDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Schedule;
import model.ScheduleRequest;
import service.interfaces.IScheduleService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleService implements IScheduleService {
    @Inject
    private IScheduleDAO iScheduleDAO;

    @Inject
    private IScheduleRequestDAO iScheduleRequestDAO;
    private final Logger logger = Logger.getLogger(ScheduleService.class.getName());

    @Override
    public Message FindAll() {
        List<Schedule> schedules = this.iScheduleDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedules).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message FindAll(String startAt, String arrivalAt, boolean isReturn) throws BadRequestException {
        if (startAt == null) throw new BadRequestException("Invalid properties");
        if (arrivalAt == null && isReturn) throw new BadRequestException("Invalid properties");
        List<Schedule> schedules = this.iScheduleDAO.FindAll(startAt, arrivalAt, isReturn);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedules).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message CreateOne(ScheduleDto dto) throws InternalServerException, BadRequestException {
        if (Objects.equals(dto.getArrivalAt(), dto.getStartAt()) || Long.parseLong(dto.getArrivalAt()) < Long.parseLong(dto.getStartAt()))
            throw new BadRequestException("Invalid properties");
        List<Schedule> conflict = this.iScheduleDAO.FindAllConflicts(dto.getTrainId(), dto.getStartAt(), dto.getArrivalAt());
        try {
            System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(conflict));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (!conflict.isEmpty()) throw new BadRequestException("Invalid properties");
        Schedule model = Helper.objectMapper(dto, Schedule.class);
        String scheduleId = IDGenerator.generate(10);
        String tripCode = IDGenerator.generate(10);
        model.setScheduleId(scheduleId);
        model.setTripCode(tripCode);
        ScheduleRequest scheduleRequest = Helper.objectMapper(model, ScheduleRequest.class);
        String requestId = IDGenerator.generate(10);
        scheduleRequest.setRequestId(requestId);
        scheduleRequest.setType("Create");
        try {
            this.iScheduleDAO.CreateOne(model);
            this.iScheduleRequestDAO.CreateOne(scheduleRequest);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            Data data = new Data.Builder(null).withResults(scheduleRequest).build();
            return new Message.Builder(meta).withData(data).build();
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
