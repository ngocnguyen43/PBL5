package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        this.message = message;
        this.errorCode = ErrorCodes.BadRequestException;
        this.statusCode = ErrorStatusCodes.BadRequestException;
    }
}
