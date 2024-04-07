package service.interfaces;

import dto.CustomerDto;
import dto.UserDto;
import utils.exceptions.api.InvalidCredentialsException;
import utils.exceptions.api.RegistrationFailedException;
import utils.response.Message;

public interface IAuthService {
    Message Register(CustomerDto dto) throws RegistrationFailedException;

    Message Login(UserDto dto) throws InvalidCredentialsException;
}
