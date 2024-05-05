package service.impl;

import dao.interfaces.IScheduleRequestDAO;
import dao.interfaces.ISeatDAO;
import dao.interfaces.ISeatsTicketsDAO;
import dto.ScheduleRequestDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.ScheduleRequest;
import model.Seat;
import model.SeatsTickets;
import service.interfaces.IScheduleRequestService;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleRequestService implements IScheduleRequestService {
    private final Logger logger = Logger.getLogger(ScheduleRequestService.class.getName());
    @Inject
    private IScheduleRequestDAO iScheduleRequestDAO;
    @Inject
    private ISeatDAO iSeatDAO;
    @Inject
    private ISeatsTicketsDAO iSeatsTicketsDAO;

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
        ScheduleRequest current = this.iScheduleRequestDAO.FindOneById(id);
        List<Seat> seats = this.iSeatDAO.FindAllByTrainId(current.getSchedule().getTrainId());
        List<SeatsTickets> seatsTicketsList = new ArrayList<>();
        if (!seats.isEmpty()) {
            seatsTicketsList = seats.stream().map(element -> {
                SeatsTickets seatsTickets = new SeatsTickets();
                seatsTickets.setSeatId(element.getSeatId());
                seatsTickets.setScheduleId(current.getScheduleId());
                seatsTickets.setCarriageId(element.getCarriageId());
                return seatsTickets;
            }).toList();
        }
        try {
            if (!Objects.equals(current.getStatus(), status)) {
                iScheduleRequestDAO.UpdateStatus(id, current.getScheduleId(), status);
                if (!seatsTicketsList.isEmpty()) {
                    this.iSeatsTicketsDAO.BulkCreate(seatsTicketsList);
                }
                current.setStatus(status);
            }
            ScheduleRequestDto dto = Helper.objectMapper(current, ScheduleRequestDto.class);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            Data data = new Data.Builder(null).withResults(dto).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }
}
