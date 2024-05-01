package utils.exceptions.api;

import utils.contants.ErrorCodes;
import utils.contants.ErrorStatusCodes;
import utils.exceptions.Exception;
import utils.response.MessageResponse;

public class MethodNotAllowedException extends Exception {
    public MethodNotAllowedException() {
        this.errorCode = ErrorCodes.MethodNotAllowedException;
        this.statusCode = ErrorStatusCodes.MethodNotAllowedException;
        this.message = MessageResponse.METHOD_NOT_ALLOWED;
    }
}
