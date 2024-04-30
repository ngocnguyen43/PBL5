package service.interfaces;

import dto.CustomerDto;
import dto.UserDto;
import utils.exceptions.api.BadRequestException;
import utils.exceptions.api.InvalidCredentialsException;
import utils.exceptions.api.RegistrationFailedException;
import utils.exceptions.server.InternalServerException;
import utils.response.Message;

public interface IAuthService {
    Message Register(CustomerDto dto) throws RegistrationFailedException, BadRequestException, InternalServerException;

    Message Login(UserDto dto) throws InvalidCredentialsException;
}
