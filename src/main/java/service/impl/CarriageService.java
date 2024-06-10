package service.impl;

import dao.interfaces.ICarriageDAO;
import dao.interfaces.ISeatDAO;
import dto.CarriageDto;
import dto.SeatPriceDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Carriage;
import model.SeatStatus;
import service.interfaces.ICarriageService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarriageService implements ICarriageService {
    private final Logger logger = Logger.getLogger(CarriageService.class.getName());
    @Inject
    private ICarriageDAO iCarriageDAO;

    @Inject
    private ISeatDAO iSeatDAO;

    @Override
    public Message FindAll() {
        List<Carriage> carriages = this.iCarriageDAO.FindAll();
        Data data = new Data.Builder(null).withResults(carriages).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message CreateOne(CarriageDto dto) throws BadRequestException, InternalServerException {
        Carriage carriage = Helper.objectMapper(dto, Carriage.class);
        String id = IDGenerator.generate(10);
        carriage.setId(id);
        try {
            this.iCarriageDAO.CreateOne(carriage);
            Carriage createdTrain = this.iCarriageDAO.FindOne(id);
            Data data = new Data.Builder(null).withResults(createdTrain).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            this.logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }

    @Override
    public Message UpdateOne(CarriageDto dto, String id) throws BadRequestException, InternalServerException {
        if (id == null) throw new BadRequestException("Invalid properties");
        Carriage updateData = Helper.objectMapper(dto, Carriage.class);
        updateData.setId(id);
        try {
            this.iCarriageDAO.UpdateOne(updateData);
            Carriage carriage = this.iCarriageDAO.FindOne(id);
            Data data = new Data.Builder(null).withResults(carriage).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @Override
    public Message FindOne(String id, String scheduleId) throws BadRequestException {
        if (id == null || scheduleId == null) throw new BadRequestException("Invalid properties");
        Carriage carriage = this.iCarriageDAO.FindOne(id);
        List<SeatStatus> seatStatus = this.iSeatDAO.FindAllSeatsStatusByCarriageId(id, scheduleId);
        List<SeatPriceDto> availableSeats = new java.util.ArrayList<>(seatStatus.stream().map(element -> {
            if (Objects.equals(element.getStatus(), "Available")) {
                SeatPriceDto dto = new SeatPriceDto();
                dto.setSeatPrice(element.getPrice());
                dto.setSeatNumber(element.getSeatNumber());
                return dto;
            }
            return null;
        }).filter(Objects::nonNull).toList());
        availableSeats.sort(Comparator.comparingInt(SeatPriceDto::getSeatNumber));
        List<SeatPriceDto> bookedSeats = new java.util.ArrayList<>(seatStatus.stream().map(element -> {
            if (Objects.equals(element.getStatus(), "Booked")) {
                SeatPriceDto dto = new SeatPriceDto();
                dto.setSeatPrice(element.getPrice());
                dto.setSeatNumber(element.getSeatNumber());
                return dto;
            }
            return null;
        }).filter(Objects::nonNull).toList());
        bookedSeats.sort(Comparator.comparingInt(SeatPriceDto::getSeatNumber));
        carriage.setAvailableSeats(availableSeats);
        carriage.setBookedSeats(bookedSeats);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(carriage).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message DeleteOne(String id) throws BadRequestException, InternalServerException {
        try {
            this.iCarriageDAO.DeleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
