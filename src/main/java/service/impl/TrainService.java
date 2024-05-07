package service.impl;

import dao.interfaces.ICarriageDAO;
import dao.interfaces.ISeatDAO;
import dao.interfaces.ITrainDAO;
import dto.TrainDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import model.Carriage;
import model.Seat;
import model.Train;
import service.interfaces.ITrainService;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.helper.Helper;
import utils.helper.IDGenerator;
import utils.response.Data;
import utils.response.Message;
import utils.response.MessageResponse;
import utils.response.Meta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainService implements ITrainService {

    private final Logger logger = Logger.getLogger(TrainService.class.getName());
    @Inject
    private ITrainDAO iTrainDAO;
    @Inject
    private ICarriageDAO iCarriageDAO;
    @Inject
    private ISeatDAO iSeatDAO;

    @Override
    public Message FindAll() {
        List<Train> trains = this.iTrainDAO.FindAll();
        Data data = new Data.Builder(null).withResults(trains).build();
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message CreateOne(TrainDto dto) throws BadRequestException, InternalServerException {
        Train train = Helper.objectMapper(dto, Train.class);
        String id = IDGenerator.generate(10);
        train.setId(id);
        List<Carriage> carriages = new ArrayList<>();
        for (int i = 0; i < dto.getTotalCarriage(); i++) {
            Carriage carriage = new Carriage();
            carriage.setTrainId(id);
            carriage.setName(IDGenerator.generate(5));
            carriage.setTotalSeats(40);
            carriage.setId(IDGenerator.generate(10));
            carriages.add(carriage);
        }
        List<Seat> seats = new ArrayList<>();
        for (Carriage carriage : carriages) {
            int i = 1;
            for (int j = 1; j <= carriage.getTotalSeats(); j++) {
                Seat seat = new Seat();
                seat.setSeatId(IDGenerator.generate(10));
                seat.setCarriageId(carriage.getId());
                seat.setSeatNumber(j);
                seat.setPrice(new BigDecimal("124567.09"));
                seats.add(seat);
            }
        }
        try {
            this.iTrainDAO.CreateOne(train);
            this.iCarriageDAO.BulkCreate(carriages);
            this.iSeatDAO.BulkCreate(seats);
            Train createdTrain = this.iTrainDAO.FindOne(id);
            Data data = new Data.Builder(null).withResults(createdTrain).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }

    @Override
    public Message UpdateOne(TrainDto dto, String id) throws BadRequestException, InternalServerException {
        if (id == null) throw new BadRequestException("Invalid properties");
        Train updateData = Helper.objectMapper(dto, Train.class);
        updateData.setId(id);
        try {
            this.iTrainDAO.UpdateOne(updateData);
            Train train = this.iTrainDAO.FindOne(id);
            Data data = new Data.Builder(null).withResults(train).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }

    @Override
    public Message FindOne(String id) {
        Train train = this.iTrainDAO.FindOne(id);
        Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
        Data data = new Data.Builder(null).withResults(train).build();
        return new Message.Builder(meta).withData(data).build();
    }

    @Override
    public Message DeleteOne(String id) throws BadRequestException, InternalServerException {
        if (id == null) throw new BadRequestException("Invalid properties");
        try {
            this.iTrainDAO.DeleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
