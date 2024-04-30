package service.impl;

import dao.interfaces.ITrainDAO;
import dto.TrainDto;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainService implements ITrainService {

    private final Logger logger = Logger.getLogger(TrainService.class.getName());
    @Inject
    private ITrainDAO iTrainDAO;

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

        try {
            this.iTrainDAO.CreateOne(train);
            Train createdTrain = this.iTrainDAO.FindOne(id);
            Data data = new Data.Builder(null).withResults(createdTrain).build();
            Meta meta = new Meta.Builder(HttpServletResponse.SC_CREATED).withMessage(MessageResponse.CREATED).build();
            return new Message.Builder(meta).withData(data).build();
        } catch (Exception e) {
            this.logger.log(Level.WARNING, e.getMessage());
            throw new InternalServerException();
        }
    }

    @Override
    public Message UpdateOne(TrainDto dto, String id) throws BadRequestException, InternalServerException {
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
        try {
            this.iTrainDAO.DeleteOne(id);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_OK).withMessage(MessageResponse.OK).build();
            return new Message.Builder(meta).build();
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
