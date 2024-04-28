package service.interfaces;

import dto.CarriageDto;
import utils.exceptions.api.BadRequestException;
import utils.response.Message;

public interface ICarriageService {
    Message FindAll();

    Message CreateOne(CarriageDto dto) throws BadRequestException;

    Message UpdateOne(CarriageDto dto, String id) throws BadRequestException;

    Message FindOne(String id);

    Message DeleteOne(String id) throws BadRequestException;

}
