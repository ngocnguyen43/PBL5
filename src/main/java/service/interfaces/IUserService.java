package service.interfaces;

import dto.UserDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IUserService {
    Message FindOne(String id);

    Message FindRole(String id);

    Message FindOneById(String id);

    Message UpdateOne(String id, UserDto userDto) throws BadRequestException, InternalServerException;

    Message DeleteOne(String id) throws InternalServerException;

}
