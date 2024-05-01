package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;
import utils.response.MessageResponse;

public class ForbiddenException extends Exception {
    public ForbiddenException() {
        this.errorCode = ErrorCodes.ForbiddenException;
        this.statusCode = ErrorStatusCodes.ForbiddenException;
        this.message = MessageResponse.FORBIDDEN;
    }
}
