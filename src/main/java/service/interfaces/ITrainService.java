package service.interfaces;

import dto.TrainDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface ITrainService {
    Message FindAll();

    Message CreateOne(TrainDto dto) throws BadRequestException, InternalServerException;

    Message UpdateOne(TrainDto dto,String id) throws BadRequestException, InternalServerException;

    Message FindOne(String id);

    Message DeleteOne(String id) throws BadRequestException, InternalServerException;
}
