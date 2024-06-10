package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.*;
import dto.FindAllSchedulesDto;
import dto.ScheduleDto;
import dto.UpdateScheduleStatusDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Carriage;
import model.Schedule;
import model.ScheduleRequest;
import model.Station;
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
    private final Logger logger = Logger.getLogger(ScheduleService.class.getName());
    @Inject
    private IScheduleDAO iScheduleDAO;
    @Inject
    private IScheduleRequestDAO iScheduleRequestDAO;
    @Inject
    private IStationDAO iStationDAO;
    @Inject
    private ICarriageDAO iCarriageDAO;
    @Inject
    private ISeatDAO iSeatDAO;

    @Override
    public Message FindAll() {
        List<Schedule> schedules = this.iScheduleDAO.FindAll();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedules).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message FindAll(String startAt, String arrivalAt, String start, String arrival, boolean isReturn) throws BadRequestException {
        if (startAt == null || start == null || arrival == null) throw new BadRequestException("Invalid properties");
        if (arrivalAt == null && isReturn) throw new BadRequestException("Invalid properties");
        List<Station> stations = this.iStationDAO.FindAll();
        Station stationStart = stations.stream().filter(e -> Objects.equals(e.getStationPoint(), start)).findAny().orElse(null);
        Station stationArrival = stations.stream().filter(e -> Objects.equals(e.getStationPoint(), arrival)).findAny().orElse(null);
        if (stationStart == null) throw new BadRequestException("Invalid start point");
        if (stationArrival == null) throw new BadRequestException("Invalid arrival point");


        List<Schedule> schedules = this.iScheduleDAO.FindAll(startAt, arrivalAt, stationStart.getStationId(), stationArrival.getStationId(), isReturn);
        List<Schedule> departures = new java.util.ArrayList<>(schedules.stream().map(e ->
                {
                    if (Objects.equals(e.getDeparturePoint(), stationStart.getStationId())) {
                        return e;
                    }
                    return null;
                }
        ).toList());
        departures.remove(null);

        List<Schedule> returns = new java.util.ArrayList<>(schedules.stream().map(e ->
                {
                    if (Objects.equals(e.getArrivalPoint(), stationArrival.getStationId())) {
                        return e;
                    }
                    return null;
                }
        ).toList());
        departures.remove(null);
        returns.remove(null);

        FindAllSchedulesDto dto = new FindAllSchedulesDto();
        dto.setDepartures(departures);
        dto.setReturns(returns);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(dto).build();
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

    @Override
    public Message FindOne(String id) {
        List<Carriage> carriages = this.iCarriageDAO.FindAllByScheduleId(id);
        Schedule schedule = this.iScheduleDAO.FindOne(id);
        schedule.setCarriages(carriages);

        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(schedule).build();
        return new Message.Builder(meta).withData(data).build();
    }
}
