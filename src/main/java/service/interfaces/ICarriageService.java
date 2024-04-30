package service.interfaces;

import dto.CarriageDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface ICarriageService {
    Message FindAll();

    Message CreateOne(CarriageDto dto) throws BadRequestException, InternalServerException;

    Message UpdateOne(CarriageDto dto, String id) throws BadRequestException, InternalServerException;

    Message FindOne(String id);

    Message DeleteOne(String id) throws BadRequestException, InternalServerException;

}
